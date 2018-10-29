package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("fr/esgi/kiosk/views/home.fxml"));
        primaryStage.setTitle("Let\'s Eat");
        Scene mainScene = new Scene(root, 1248, 1169);
        primaryStage.minHeightProperty().bind(mainScene.heightProperty());
        primaryStage.minWidthProperty().bind(mainScene.widthProperty());
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


    public static void main(String[] args){


        launch(args);
    }
}
