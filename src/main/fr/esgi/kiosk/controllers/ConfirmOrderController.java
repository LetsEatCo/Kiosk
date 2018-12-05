package main.fr.esgi.kiosk.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import main.fr.esgi.kiosk.helpers.StageManagerHelper;
import main.fr.esgi.kiosk.helpers.UIHelper;
import main.fr.esgi.kiosk.views.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ConfirmOrderController implements FxmlController{

    @FXML
    private VBox root;
    private final StageManagerHelper stageManagerHelper;

    @Autowired
    @Lazy
    public ConfirmOrderController(StageManagerHelper stageManagerHelper) {
        this.stageManagerHelper = stageManagerHelper;
    }

    @Override
    public void initialize() {

        UIHelper.makeFadeInTransition(root);

        Thread sleep = new Thread(() -> Platform.runLater(this::sleep));
        sleep.start();
    }

    private void sleep() {

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UIHelper.makeFadeOutTransition(root,stageManagerHelper,FxmlView.HOME);

    }
}
