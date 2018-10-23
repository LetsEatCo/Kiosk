package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.fr.esgi.kiosk.routes.Stores;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        String email = "toto@toto.fr";
        String password = "test";
        Stores.login(email,password);

//        Parent root = FXMLLoader.load(getClass().getResource("fr/esgi/kiosk/views/home.fxml"));
//        primaryStage.setTitle("Let\'s Eat");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
