package main.fr.esgi.kiosk.controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.fr.esgi.kiosk.helpers.StageManagerHelper;
import main.fr.esgi.kiosk.helpers.UIHelper;
import main.fr.esgi.kiosk.routes.StoreRouter;
import main.fr.esgi.kiosk.views.FxmlView;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MainController implements FxmlController{

    @FXML
    private VBox root;

    @FXML
    private HBox adminRoot;

    @FXML
    private JFXTextField emailInput;

    @FXML
    private JFXPasswordField passwordInput;

    private final StageManagerHelper stageManagerHelper;

    @Autowired @Lazy
    public MainController(StageManagerHelper stageManagerHelper) {
        this.stageManagerHelper = stageManagerHelper;
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

        UIHelper.makeFadeOutTransition(adminRoot, stageManagerHelper, FxmlView.HOME);
    }

    @FXML
    void placeToEat(ActionEvent event){

        UIHelper.makeFadeOutTransition(root, stageManagerHelper, FxmlView.LOCATION);

    }

    @Override
    public void initialize() {
        UIHelper.makeFadeInTransition(root);
    }
}
