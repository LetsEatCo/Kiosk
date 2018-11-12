package main.fr.esgi.kiosk.helpers;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.fr.esgi.kiosk.controllers.CommandController;
import main.fr.esgi.kiosk.models.RessourceElementProduct;
import main.fr.esgi.kiosk.models.ui.ElementUI;
import main.fr.esgi.kiosk.models.ui.OptionMealUI;
import main.fr.esgi.kiosk.views.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class UIHelper {


    private static CommandController commandController;

    @Autowired
    public UIHelper(CommandController commandController) {

        UIHelper.commandController = commandController;
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

    public static Parent loadFxml(String path) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(UIHelper.class.getResource(path));
        return fxmlLoader.load();
    }

    public static <T extends RessourceElementProduct> ArrayList<ElementUI> createProductsElementsUI(ArrayList<T> productsElements){

        ArrayList<ElementUI> productElementUIArrayList = new ArrayList<>();

        for(T productElement : productsElements){

            ElementUI productElementUI = new ElementUI(productElement, commandController);
            productElementUIArrayList.add(productElementUI);

        }

        return productElementUIArrayList;

    }

    public static <T extends RessourceElementProduct> ArrayList<OptionMealUI> createProductsElementsOptionsUI(ArrayList<T> productsElements, Object controller){

        ArrayList<OptionMealUI> productElementUIArrayList = new ArrayList<>();

        for(T productElement : productsElements){

            OptionMealUI productElementUI = new OptionMealUI(productElement, controller);
            productElementUIArrayList.add(productElementUI);

        }

        return productElementUIArrayList;

    }

    public static <T> void loadUIContent(ArrayList<T> elementUI, Pane content) {

        int size = elementUI.size();

        for(int i =0; i<size; i++){

            content.getChildren().add((Node) elementUI.get(i));
        }

    }

}
