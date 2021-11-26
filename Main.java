package application;
	
import java.sql.Date;
import java.sql.SQLException;
import application.ReviewPosts.PostButton;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Main extends Application {
	final double CENTER_ON_SCREEN_X_FRACTION = 1.0f / 2;
	final double CENTER_ON_SCREEN_Y_FRACTION = 1.0f / 3;
	
	private User user;
	private LoginPage lp;
	private SignUpPage sup;
	private HomePage hp;
	private FreeForm ff;
	private StructuredForm sf;
	private ReviewPosts rp;
	private Stage stage;
	private Model model;
	private DB db;
	private Screen screen;
	private Rectangle2D bounds;
	
	
	@Override
	public void start(Stage primaryStage) throws SQLException{
		stage = primaryStage;
		screen = Screen.getPrimary();
		bounds = screen.getVisualBounds();
		db = new DB();
		model = new Model();
		lp = new LoginPage();
		lp.addLoginHandler(new LoginHandler());
		lp.addSignupHandler(new SignUpPageHandler());
		stage.setScene(lp.setLoginScene());
        stage.show();
        setCenter();
        
	}
	
	public void setCenter() {
		stage.setX((bounds.getWidth() - stage.getWidth()) / 2);
		stage.setY((bounds.getHeight() - stage.getHeight()) / 2);		
	}

	private class FormHandler implements EventHandler<ActionEvent>{
		PostPage pp;
		Page previous;
		String type, text;
		public FormHandler(Page previous,String type,String text) {
			this.previous = previous;
			this.type = type;
			this.text = text;
		}
		@Override
		public void handle(ActionEvent arg0) {
			init(type);
			if(this.text!="")
				pp.setText(this.text);
		}
		public void init(String type) {
			if(type == "structured")
				pp = new StructuredForm(previous,model);
			else
				pp = new FreeForm(previous);
			pp.addBackHandler(new BackHandler(pp));
			pp.addSavePostHandler(new SavePostHandler(pp));
			pp.setHeight(bounds.getHeight());
			pp.setWidth(hp.getWidth());
			stage.setHeight(bounds.getHeight());
			stage.setWidth(pp.getWidth());
			stage.setScene(pp.setScene());
			setCenter();
		}
	}
	
	private class ReviewHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			rp = new ReviewPosts(hp, user);
			rp.addBackHandler(new BackHandler(rp));
			ObservableList<Node> buttons = rp.getPostButtons();
			for(Node n: buttons) {
				PostButton pb = (PostButton)n;
				String type = pb.getPost().getType();
				String text = pb.getPost().getText();
				pb.setOnAction(new FormHandler(rp,type, text));
			}
			stage.setWidth(rp.getWidth());
			stage.setHeight(rp.getHeight());
			stage.setScene(rp.setScene());
			setCenter();
		}
		
	}
	
	private class BackHandler implements EventHandler<ActionEvent>{
		private Page page;
		public BackHandler(Page page) {
			this.page = page;
		}
		@Override
		public void handle(ActionEvent arg0) {
			stage.setHeight(page.getPrevious().getHeight());
			stage.setWidth(page.getPrevious().getWidth());
			stage.setScene(page.getPrevious().setScene());
			setCenter();
		}
		
	}
	
	private class SavePostHandler implements EventHandler<ActionEvent>{
		private PostPage page;
		
		public SavePostHandler(PostPage page) {
			this.page = page;
		}
		@Override
		public void handle(ActionEvent arg0) {
			Date date = page.getDate();
			String type = page.getName();
			String text = page.getText();
			String un = user.getUsername();
			try {
			DB.savePost(un,type,date,text);
			}catch (SQLException e) {
				Alert.display(e.getMessage());
			}
			stage.setHeight(page.getPrevious().getHeight());
			stage.setWidth(page.getPrevious().getWidth());
			stage.setScene(page.getPrevious().setScene());
			setCenter();
			Alert.display("Post saved successfully");
		}
		
	}
	
	private class SignUpHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			try {
			user = DB.addUser(sup.getUserName(), sup.getPw(), sup.getFname(), sup.getLname());
			hp = new HomePage(user);
			hp.addFFormHandler(new FormHandler(hp,"freeform",""));
            hp.addSFormHandler(new FormHandler(hp,"structured",""));
			hp.addReviewHandler(new ReviewHandler());
			stage.setScene(hp.setScene());
			setCenter();
			}catch (SQLException e) {
				Alert.display(e.getMessage());
			}
		}
	}
	
	private class SignUpPageHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			sup = new SignUpPage();
			sup.addSignupHandler(new SignUpHandler());
			stage.setScene(sup.setSignUpScene());
			setCenter();
		}
	}
	
	private class LoginHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            String username = lp.getUser();
            String pw = lp.getPw();
            try {
                user = DB.authenticate(username, pw);
                hp = new HomePage(user);
                hp.addFFormHandler(new FormHandler(hp,"freeform",""));
                hp.addSFormHandler(new FormHandler(hp,"structured",""));
                hp.addReviewHandler(new ReviewHandler());
    			stage.setScene(hp.setScene());
    			setCenter();
            }catch (IllegalArgumentException e){
                Alert.display("Couldn't find username or password");
            }
        }
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
