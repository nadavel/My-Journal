package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class StructuredForm extends PostPage{
	private Model model;
	private Button change, goDeep, follow;
	private Label primary, related, followup;
	
	public StructuredForm(Page previous, Model model) {
		super(previous);
		this.model = model;
		name = "structured";
		primary = new Label();
		primary.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,FontPosture.ITALIC,16));
		related = new Label();
		related.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,FontPosture.ITALIC,16));
		followup = new Label();
		followup.setFont(Font.font("Comic Sans MS",FontWeight.BOLD,FontPosture.ITALIC,16));
		setLabels();	
		change = new Button("Change");
		goDeep = new Button("Deeper");
		follow = new Button("Followup");
		change.setOnAction(e->replacePrimary());
		goDeep.setOnAction(e->textArea.appendText("\n"+related.getText()+"\n"+"\n"));
		follow.setOnAction(e->textArea.appendText("\n"+followup.getText()+"\n"+"\n"));
        topBtn.getChildren().addAll(change, goDeep, follow);
        textArea.appendText(primary.getText()+"\n"+"\n");
		textArea.positionCaret(textArea.getLength()+1);
	}
	
	
	public void replacePrimary() {
		String text = textArea.getText();
		String old = primary.getText();
		setLabels();
		textArea.setText(text.replace(old, primary.getText()));
	}
	
	public void setLabels() {
		Question q = model.getQuestion();
		primary.setText(q.getPrimary());
		related.setText(q.getRelated());
		followup.setText(q.getFollowup());
	}

}

