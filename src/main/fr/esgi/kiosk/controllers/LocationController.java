package main.fr.esgi.kiosk.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import main.fr.esgi.kiosk.helpers.UIHelper;

public class LocationController {


    @FXML
    void openCommandHome(ActionEvent event){

        Stage rootStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        UIHelper.loadWindow("/main/fr/esgi/kiosk/views/commandHome.fxml", "Command", rootStage);

    }

}
