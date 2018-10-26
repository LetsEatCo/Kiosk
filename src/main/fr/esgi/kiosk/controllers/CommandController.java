package main.fr.esgi.kiosk.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.fr.esgi.kiosk.helpers.HttpHelper;
import main.fr.esgi.kiosk.helpers.UIHelper;
import main.fr.esgi.kiosk.models.ui.CartElementUI;
import main.fr.esgi.kiosk.models.ui.ProductElementUI;
import main.fr.esgi.kiosk.routes.StoreRouter;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;

public class CommandController {

    private int adminCounter = 0;

    @FXML
    private Pane mainContent;

    @FXML
    private VBox cartPane;

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
        loadContent(menu);


    }

    @FXML
    void loadProducts() throws IOException {

        String products = "/main/fr/esgi/kiosk/views/products.fxml";
        loadContent(products);


    }

    @FXML
    void loadDesserts() throws IOException {

        String desserts = "/main/fr/esgi/kiosk/views/desserts.fxml";
        loadContent(desserts);

    }

    @FXML
    void order(ActionEvent event)  {

        System.out.println("Order process...");

    }

    private void loadContent(String viewPath) throws IOException {

        Parent fxml = UIHelper.loadFxml(viewPath);
        mainContent.getChildren().removeAll();
        mainContent.getChildren().setAll(fxml);
    }
}
