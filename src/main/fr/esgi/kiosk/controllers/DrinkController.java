package main.fr.esgi.kiosk.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import main.fr.esgi.kiosk.helpers.StageManagerHelper;
import main.fr.esgi.kiosk.helpers.UIHelper;
import main.fr.esgi.kiosk.models.Cart;
import main.fr.esgi.kiosk.models.RessourceElementProduct;
import main.fr.esgi.kiosk.views.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class DrinkController<T extends RessourceElementProduct> implements FxmlController{

    @FXML
    private VBox root;

    private Cart<T> cart;
    private T selectedProductElement;

    private final StageManagerHelper stageManagerHelper;

    @Autowired @Lazy
    public DrinkController(StageManagerHelper stageManagerHelper) {
        this.stageManagerHelper = stageManagerHelper;
    }

    public void setSelectedProductElement(T selectedProductElement) {
        this.selectedProductElement = selectedProductElement;
    }

    @Override
    public void initialize() {

        UIHelper.makeFadeInTransition(root);
    }

    @FXML
    void desserts(){
        UIHelper.makeFadeOutTransition(root, stageManagerHelper, FxmlView.DESSERTS);
    }

    @FXML
    void accompaniment(){
        UIHelper.makeFadeOutTransition(root, stageManagerHelper, FxmlView.ACCOMPANIMENT);
    }
}
