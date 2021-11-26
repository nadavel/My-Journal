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

public class LoginPage {
    //components
    private GridPane grid;
    private Scene scene;
    private Text scenetitle;
    private Label userName, pw;
    private TextField userTextField;
    private PasswordField pwBox;
    private Button login, signup;
    private HBox hbBtn;

    public LoginPage(){
        //layout
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        scene = new Scene(grid, 300, 275);
        //add components
        scenetitle = new Text("Welcome");
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
        //add buttons
        login = new Button("Log in");
        signup = new Button("Sign up");
        hbBtn = new HBox(50);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(signup,login);
        grid.add(hbBtn, 1, 4);
    }

    public void addLoginHandler(EventHandler<ActionEvent> leh){
        login.setOnAction(leh);
    }

    public void addSignupHandler(EventHandler<ActionEvent> seh){
        signup.setOnAction(seh);
    }
    

    public Scene setLoginScene(){
        return scene;
    }

    public String getUser() {
        return userTextField.getText();
    }

    public String getPw() {
        return pwBox.getText();
    }
}