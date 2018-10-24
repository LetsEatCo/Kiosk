package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.fr.esgi.kiosk.routes.Stores;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("fr/esgi/kiosk/views/home.fxml"));
        primaryStage.setTitle("Let\'s Eat");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException, ParseException {


//        launch(args);
    }
}
