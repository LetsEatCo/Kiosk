package main.fr.esgi.kiosk.helpers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class UIHelper {


    public static void loadWindow(String path, String title, Stage rootStage){

        try {
            Parent root = FXMLLoader.load(UIHelper.class.getResource(path));

            rootStage.setTitle(title);
            Scene myScene =new Scene(root);

            rootStage.setScene(myScene);

            rootStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void returnHome(ActionEvent event) {

        String home ="/main/fr/esgi/kiosk/views/home.fxml";

        Stage rootStage = (Stage)((Node)event.getSource()).getScene().getWindow();

        loadWindow(home , "Home", rootStage);

    }

    public static Parent loadFxml(String path) throws IOException {

        return FXMLLoader.load(UIHelper.class.getResource(path));
    }

}
