package main.fr.esgi.kiosk.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.fr.esgi.kiosk.helpers.StageHelper;

import java.io.IOException;

public class CommandController {

    private int adminCounter = 0;

    @FXML
    private Pane mainContent = null;

    @FXML
    void adminRegistration(ActionEvent event) {

        adminCounter +=1;

        if(adminCounter == 10) {

            Stage rootStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            StageHelper.loadWindow("/main/fr/esgi/kiosk/views/login.fxml", "Admin Login", rootStage);

            adminCounter=0;
        }
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

    private void loadContent(String viewPath) throws IOException {

        Parent fxml = StageHelper.loadFxml(viewPath);
        mainContent.getChildren().removeAll();
        mainContent.getChildren().setAll(fxml);
    }
}
