package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Confirm {
	static boolean answer;
	
    public static boolean display(String msg){
    	Stage stage = new Stage();	
    	GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
    	Button yes = new Button("Yes");
    	Button no = new Button("No");
    	yes.setOnAction(e->{
    		answer = true;
    		stage.close();
    	});
        no.setOnAction(e->{
        	answer = false;
        	stage.close();
        });
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Please confirm:");
        stage.setMinWidth(300);
        stage.setMinHeight(200);
        Label label = new Label(msg);
        grid.add(label, 0, 0);
        Scene scene = new Scene(grid);
        HBox hbBtn = new HBox(50);
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().addAll(yes, no);
        grid.add(hbBtn, 0, 1);
        stage.setScene(scene);
        stage.showAndWait();
        
        return answer;
    }
}
