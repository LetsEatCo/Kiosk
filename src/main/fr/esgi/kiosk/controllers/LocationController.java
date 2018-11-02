package main.fr.esgi.kiosk.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import main.fr.esgi.kiosk.helpers.StageManagerHelper;
import main.fr.esgi.kiosk.helpers.UIHelper;
import main.fr.esgi.kiosk.views.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class LocationController implements FxmlController {

    private final StageManagerHelper stageManagerHelper;

    @Autowired
    @Lazy
    public LocationController(StageManagerHelper stageManagerHelper) {
        this.stageManagerHelper = stageManagerHelper;
    }

    @FXML
    void openCommandHome(ActionEvent event){

        stageManagerHelper.switchScene(FxmlView.COMMAND_HOME);

    }

    @FXML
    void home(ActionEvent event){

        stageManagerHelper.switchScene(FxmlView.HOME);
    }

    @Override
    public void initialize() {

    }
}
