package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ReviewPosts extends Page{
	private ArrayList<Post> posts;
	private ResultSet rs;
	private VBox layout;
	private Button back;
	private GridPane grid;
	private User user;
	
	public ReviewPosts(Page previous, User user) {
		this.user = user;
		this.previous = previous;
		
		width = 400; height = 350;
		scenetitle = new Text("Click on a post to view/edit");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		layout = new VBox(30);
		grid = new GridPane();		
		grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setAlignment(Pos.CENTER);
        init();				
        for (int i = 0; i < posts.size(); i++) {
        	PostButton pb = new PostButton(posts.get(i));
        	grid.add(pb,i%2,i/2,1,1);
        }
        ScrollPane scrollPane = new ScrollPane();
        back = new Button("Back");
        layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        scrollPane.setContent(grid);
        scrollPane.setFitToWidth(true);        
        layout.getChildren().addAll(scenetitle,scrollPane, back);
        scene = new Scene(layout,width,height);
	}

	public void init() {
		posts = new ArrayList<>();
		try {
			rs = DB.getPastPosts(user);
			while(rs.next()) {
				posts.add(new Post(rs.getString("type"),rs.getDate("date"),rs.getString("text")));
			}
		}catch (SQLException e) {
			scenetitle.setText(e.getMessage());
		}
	}
	
	 class PostButton extends Button{
		private Post post;
		private double width;
		private double height;
		
		PostButton(Post post){
			this.post = post;
			String text = String.format("%s \n %s", post.getDate().toString(),post.getType());
			height = 112; width = 63;
			this.setMinHeight(height);
			this.setMinWidth(width);
			this.setText(text);
		}
		
		public Post getPost() {
			return post;
		}
	}
	 
	 public ObservableList<Node> getPostButtons() {
		return grid.getChildren();
	}
	
	public void addBackHandler(EventHandler<ActionEvent> bh) {
		back.setOnAction(bh);
	}
}
