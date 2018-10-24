package main.fr.esgi.kiosk.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import main.fr.esgi.kiosk.helpers.StageHelper;
import main.fr.esgi.kiosk.routes.StoreRouter;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class HomeController {

    private int adminCounter = 0 ;

    @FXML
    private JFXButton startBtn;

    @FXML
    private JFXTextField emailInput;

    @FXML
    private JFXPasswordField passwordInput;

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
    void login() throws IOException, ParseException {

        String email = emailInput.getText();
        String password = passwordInput.getText();
        StoreRouter storeRouter = new StoreRouter();
        storeRouter.login(email, password);

    }

    @FXML
    void home(ActionEvent event){

        StageHelper.returnHome(event);
    }

    @FXML
    void placeToEat(ActionEvent event){

        Stage rootStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        String locationPlace = "/main/fr/esgi/kiosk/views/location.fxml";
        StageHelper.loadWindow(locationPlace, "Place To Eat", rootStage);

    }

}
