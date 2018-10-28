package main.fr.esgi.kiosk.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.fr.esgi.kiosk.helpers.JsonHelper;
import main.fr.esgi.kiosk.helpers.UIHelper;
import main.fr.esgi.kiosk.models.Product;
import main.fr.esgi.kiosk.models.ui.ProductElementUI;
import main.fr.esgi.kiosk.routes.StoreRouter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CommandController implements Initializable {

    private int adminCounter = 0;
    private ArrayList<Product> productsArrayList;
    private ArrayList<ProductElementUI> productElementUIArrayList;
    private JSONObject store;
    private JSONArray meals;

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
    void loadMenu() throws IOException {

        String menu = "/main/fr/esgi/kiosk/views/menu.fxml";
        loadUIContent(menu);
        System.out.println(meals);


    }

    @FXML
    void loadProducts() {

        loadUIContent(productElementUIArrayList);

    }

    @FXML
    void loadDesserts() throws IOException {

        String desserts = "/main/fr/esgi/kiosk/views/desserts.fxml";
        loadUIContent(desserts);

    }

    @FXML
    void order(ActionEvent event)  {

        System.out.println("Order process...");

    }

    private void loadUIContent(String viewPath) throws IOException {

        Parent fxml = UIHelper.loadFxml(viewPath);
        mainContent.getChildren().removeAll();
        mainContent.getChildren().setAll(fxml);
    }

    private void loadUIContent(ArrayList<ProductElementUI> productElementUIS) {

        VBox vBox = new VBox();

        int size = productElementUIS.size();
        int productsIndexJourney = 0;
        for(int i =0; i<size; i++){

            HBox hBox = new HBox();

            for(int j=productsIndexJourney;j<productElementUIS.size();j++){

                hBox.getChildren().add(productElementUIS.get(productsIndexJourney));
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
        JSONArray products = (JSONArray) store.get("products");
        meals = (JSONArray) store.get("meals");

        productsArrayList = JsonHelper.parseJsonProducts(products);
        productElementUIArrayList = UIHelper.createProductsUI(productsArrayList);

    }


}
