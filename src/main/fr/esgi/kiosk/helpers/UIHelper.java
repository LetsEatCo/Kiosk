package main.fr.esgi.kiosk.helpers;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.fr.esgi.kiosk.models.RessourceElementProduct;
import main.fr.esgi.kiosk.models.ui.ElementUI;
import main.fr.esgi.kiosk.views.FxmlView;

import java.io.IOException;
import java.util.ArrayList;

public class UIHelper {

    private final Stage primaryStage;

    public UIHelper(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public static void makeFadeInTransition(Parent root) {
        FadeTransition fadeTransition = new FadeTransition();

        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(root);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    public static void makeFadeOutTransition(Parent root, StageManagerHelper stageManagerHelper, FxmlView view) {
        FadeTransition fadeTransition = new FadeTransition();

        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(root);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(event1 -> stageManagerHelper.switchScene(view));
        fadeTransition.play();
    }

    public static Parent loadFxml(String path, Object controller) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(UIHelper.class.getResource(path));
        fxmlLoader.setController(controller);
        return fxmlLoader.load();
    }

    public static <T extends RessourceElementProduct> ArrayList<ElementUI> createProductsElementsUI(ArrayList<T> productsElements){

        ArrayList<ElementUI> productElementUIArrayList = new ArrayList<>();

        for(T productElement : productsElements){

            ElementUI productElementUI = new ElementUI(productElement);
            productElementUIArrayList.add(productElementUI);

        }

        return productElementUIArrayList;

    }

}
