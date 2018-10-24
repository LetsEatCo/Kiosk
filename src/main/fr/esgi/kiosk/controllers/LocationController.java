package main.fr.esgi.kiosk.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import main.fr.esgi.kiosk.helpers.StageHelper;

public class LocationController {


    @FXML
    void openCommandHome(ActionEvent event){

        Stage rootStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        StageHelper.loadWindow("/main/fr/esgi/kiosk/views/commandHome.fxml", "Admin Login", rootStage);

    }

}
