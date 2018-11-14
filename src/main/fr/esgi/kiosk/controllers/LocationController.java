package main.fr.esgi.kiosk.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import main.fr.esgi.kiosk.helpers.StageManagerHelper;
import main.fr.esgi.kiosk.helpers.UIHelper;
import main.fr.esgi.kiosk.models.Order;
import main.fr.esgi.kiosk.views.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class LocationController implements FxmlController {

    private Order order;
    @FXML
    private BorderPane root;

    private final StageManagerHelper stageManagerHelper;

    @Autowired
    @Lazy
    public LocationController(StageManagerHelper stageManagerHelper, Order order) {
        this.stageManagerHelper = stageManagerHelper;
        this.order = order;
    }

    @FXML
    void onTheSpot(){

        order.setTakeAway(false);
        UIHelper.makeFadeOutTransition(root, stageManagerHelper, FxmlView.COMMAND_HOME);

    }

    @FXML
    void takeAway(){
        order.setTakeAway(true);
    }

    @FXML
    void home(ActionEvent event){

        UIHelper.makeFadeOutTransition(root, stageManagerHelper, FxmlView.HOME);
    }

    @Override
    public void initialize() {
        UIHelper.makeFadeInTransition(root);
    }
}
