package main.fr.esgi.kiosk.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.fr.esgi.kiosk.helpers.StageManagerHelper;
import main.fr.esgi.kiosk.helpers.UIHelper;
import main.fr.esgi.kiosk.models.Meal;
import main.fr.esgi.kiosk.models.Product;
import main.fr.esgi.kiosk.models.Store;
import main.fr.esgi.kiosk.models.ui.ElementUI;
import main.fr.esgi.kiosk.routes.StoreRouter;
import main.fr.esgi.kiosk.views.FxmlView;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Component
public class CommandController implements FxmlController {

    private int adminCounter = 0;
    private ArrayList<Product> products;
    private ArrayList<Meal> meals;
    private ArrayList<ElementUI> productElementUIArrayList;
    private ArrayList<ElementUI> mealsElementUIArrayList;
    private Store store;

    @FXML
    private Pane mainContent;

    @FXML
    private VBox cartPane;

    private final StageManagerHelper stageManagerHelper;

    @Autowired @Lazy
    public CommandController(StageManagerHelper stageManagerHelper) {
        this.stageManagerHelper = stageManagerHelper;
    }

    @Override
    @Lazy
    public void initialize() {
        try {
            lazyLoadProducts();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void adminRegistration(ActionEvent event) {

        adminCounter +=1;

        if(adminCounter == 10) {

            adminCounter=0;
            stageManagerHelper.switchScene(FxmlView.ADMIN_LOGIN);
        }
    }

    @FXML
    void loadPreviousPage(ActionEvent event) {
        stageManagerHelper.switchScene(FxmlView.LOCATION);
    }

    @FXML
    void loadMenu() {

        loadUIContent(mealsElementUIArrayList);

    }

    @FXML
    void loadProducts() {

        loadUIContent(productElementUIArrayList);

    }

    @FXML
    void loadDesserts() {


    }

    @FXML
    void order(ActionEvent event)  {

        System.out.println("Order process...");

    }

    private <T> void loadUIContent(ArrayList<T> elementUI) {

        VBox vBox = new VBox();

        int size = elementUI.size();
        int productsIndexJourney = 0;
        for(int i =0; i<size; i++){

            HBox hBox = new HBox();

            for(int j=productsIndexJourney;j<elementUI.size();j++){

                hBox.getChildren().add((Node) elementUI.get(productsIndexJourney));
                productsIndexJourney +=1;
            }

            vBox.getChildren().add(hBox);

        }

        mainContent.getChildren().removeAll();
        mainContent.getChildren().setAll(vBox);
    }

    private void lazyLoadProducts() throws IOException, ParseException {

        StoreRouter storeRouter = new StoreRouter();
        store = storeRouter.getStore();

        products = store.getSections().get(0).getProducts();
        meals = store.getSections().get(0).getMeals();

        productElementUIArrayList = UIHelper.createProductsElementsUI(products);
        mealsElementUIArrayList = UIHelper.createProductsElementsUI(meals);

    }



}
