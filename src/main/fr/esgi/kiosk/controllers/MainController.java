package main.fr.esgi.kiosk.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.fr.esgi.kiosk.helpers.StageManagerHelper;
import main.fr.esgi.kiosk.routes.StoreRouter;
import main.fr.esgi.kiosk.views.FxmlView;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MainController implements FxmlController{

    private int adminCounter = 0 ;

    @FXML
    private JFXButton startBtn;

    @FXML
    private JFXTextField emailInput;

    @FXML
    private JFXPasswordField passwordInput;

    private final StageManagerHelper stageManagerHelper;

    private final LocationController locationController;

    private final CommandController commandController;

    @Autowired @Lazy
    public MainController(StageManagerHelper stageManagerHelper, LocationController locationController, CommandController commandController) {
        this.stageManagerHelper = stageManagerHelper;
        this.locationController = locationController;
        this.commandController = commandController;
    }

    public LocationController getLocationController() {
        return locationController;
    }

    public CommandController getCommandController() {
        return commandController;
    }

    @FXML
    void adminRegistration(ActionEvent event) {

        adminCounter +=1;

        if(adminCounter == 10) {

//            Stage rootStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            UIHelper.loadWindow("/main/resources/fxml/AdminLogin.fxml", "Admin Login", rootStage);
            stageManagerHelper.switchScene(FxmlView.ADMIN_LOGIN);

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

        stageManagerHelper.switchScene(FxmlView.HOME);
    }

    @FXML
    void placeToEat(ActionEvent event){

//        Stage rootStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        String locationPlace = "/main/resources/fxml/Location.fxml";
//        UIHelper.loadWindow(locationPlace, "Place To Eat", rootStage);

        stageManagerHelper.switchScene(FxmlView.LOCATION);

    }

    @Override
    public void initialize() {

    }
}
