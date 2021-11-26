package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SignUpPage {
	
	private GridPane grid;
    private Scene scene;
    private Text scenetitle;
    private Label userName, pw, fname, lname;
    private TextField userTextField,fnameTextField, lnameTextField;
    private PasswordField pwBox;
    private Button signup;
    private HBox hbBtn;
    
    public SignUpPage() {
    	
    	grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        scene = new Scene(grid, 300, 275);
        
        scenetitle = new Text("Nice to meet you");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        
        userName = new Label("User Name:");
        grid.add(userName, 0, 1);
        userTextField = new TextField();
        grid.add(userTextField, 1, 1);
        pw = new Label("Password:");
        grid.add(pw, 0, 2);
        pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);
        fname = new Label("First name");
        grid.add(fname, 0, 3);
        fnameTextField = new TextField();
        grid.add(fnameTextField, 1, 3);
        lname = new Label("Last name");
        grid.add(lname, 0, 4);
        lnameTextField = new TextField();
        grid.add(lnameTextField, 1, 4);
        
        signup = new Button("Sign up");
        hbBtn = new HBox(50);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(signup);
        grid.add(hbBtn, 1, 5);
    }
    
    public void addSignupHandler(EventHandler<ActionEvent> seh){
        signup.setOnAction(seh);
    }
    
    public Scene setSignUpScene(){
        return scene;
    }

	public String getUserName() {
		return userTextField.getText();
	}

	public String getPw() {
		return pwBox.getText();
	}

	public String getFname() {
		return fnameTextField.getText();
	}

	public String getLname() {
		return lnameTextField.getText();
	}

    
}
