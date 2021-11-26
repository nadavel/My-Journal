package application;

import java.sql.Date;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public abstract class PostPage extends Page{
	protected TextArea textArea;
	protected VBox layout;
	protected HBox topBtn, buttomBtn;
	protected Button save, back;
	protected Date date;
	
	public PostPage(Page previous) {
		this.previous = previous;
		date = new Date(System.currentTimeMillis());
		textArea = new TextArea();
		textArea.setWrapText(true);
		save = new Button("Save");
		back = new Button("Back");
		buttomBtn = new HBox(10);
        buttomBtn.setAlignment(Pos.BOTTOM_CENTER);
        buttomBtn.getChildren().addAll(save, back);
        topBtn = new HBox(10);
        topBtn.setAlignment(Pos.TOP_CENTER);
		layout = new VBox(10, topBtn,textArea,buttomBtn);
		layout.setPadding(new Insets(10));
		VBox.setVgrow(textArea, Priority.ALWAYS);
		scene = new Scene(layout);
		scene.getStylesheets().add(getClass().getResource("notepad.css").toExternalForm());
		textArea.setText(date.toString()+"\n"+"\n");
	}
	
	public void addBackHandler(EventHandler<ActionEvent> bh) {
		back.setOnAction(bh);
	}
	
	public void addSavePostHandler(EventHandler<ActionEvent> sph) {
		save.setOnAction(sph);
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getText() {
		return textArea.getText();
	}
	
	public void setText(String text) {
		textArea.setText(text);
		textArea.positionCaret(textArea.getLength()+1);
	}
}
