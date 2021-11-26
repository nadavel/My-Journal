package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class HomePage extends Page{

	private GridPane grid;
    private Text scenetitle;
    private Button structured, free, review;
    private HBox hbBtn;
    private User user;
    
    public HomePage(User user) {
    	width = 500;
    	height = 275;
    	grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        scene = new Scene(grid, width, height);
        scenetitle = new Text(String.format("Welcome %s, Select an option",user.getFirstName()));
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        structured = new Button("Structured");
        free = new Button("Freeform");
        review = new Button("Review past posts");
        hbBtn = new HBox(30);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().addAll(free,structured, review);
        grid.add(hbBtn, 1, 2);
        
    }
    
    public void addReviewHandler(EventHandler<ActionEvent> rh) {
		review.setOnAction(rh);
	}
    
    public void addFFormHandler(EventHandler<ActionEvent> ffh) {
		free.setOnAction(ffh);
	}
    
    public void addSFormHandler(EventHandler<ActionEvent> sfh) {
    	structured.setOnAction(sfh);
	}
}
