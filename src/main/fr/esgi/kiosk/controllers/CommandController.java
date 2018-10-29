package main.fr.esgi.kiosk.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.fr.esgi.kiosk.helpers.UIHelper;
import main.fr.esgi.kiosk.models.Meal;
import main.fr.esgi.kiosk.models.Product;
import main.fr.esgi.kiosk.models.Store;
import main.fr.esgi.kiosk.models.ui.ElementUI;
import main.fr.esgi.kiosk.routes.StoreRouter;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CommandController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            lazyLoadProducts();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void adminRegistration(ActionEvent event) {

        adminCounter +=1;

        if(adminCounter == 10) {

            Stage rootStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            UIHelper.loadWindow("/main/fr/esgi/kiosk/views/login.fxml", "Admin Login", rootStage);

            adminCounter=0;
        }
    }

    @FXML
    void loadPreviousPage(ActionEvent event) {
        String placeToEat = "/main/fr/esgi/kiosk/views/location.fxml";
        Stage rootStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        UIHelper.loadWindow(placeToEat, "Place To Eat", rootStage);
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

        products = store.getProducts();
        meals = store.getMeals();

        productElementUIArrayList = UIHelper.createProductsElementsUI(products);
        mealsElementUIArrayList = UIHelper.createProductsElementsUI(meals);

    }


}
