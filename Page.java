package application;

import javafx.scene.Scene;
import javafx.scene.text.Text;

public abstract class Page {
	
	protected Scene scene;
	protected Text scenetitle;
	protected String name;
	protected Page previous;
	protected double width, height;
	
	public Scene setScene() {
		return scene;
	}
	
	public String getName() {
		return name;
	}
	
	public Page getPrevious() {
		return previous;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double val) {
		height = val;
	}
	
	public void setWidth(double val) {
		width = val;
	}

}
