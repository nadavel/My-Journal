package application;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Alert {

    public static void display(String msg){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Oops, something went wrong..");
        stage.setMinWidth(250);
        stage.setMinHeight(100);
        Label label = new Label(msg);
        StackPane sp = new StackPane();
        sp.getChildren().add(label);
        Scene scene = new Scene(sp);
        stage.setScene(scene);
        stage.showAndWait();
    }
}

