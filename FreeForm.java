package application;

public class FreeForm extends PostPage{
	
	public FreeForm(Page previous) {
		super(previous);
		name = "freeform";
		textArea.positionCaret(textArea.getLength()+1);		
	}
}
