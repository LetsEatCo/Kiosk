package main.fr.esgi.kiosk.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.fr.esgi.kiosk.helpers.StageManagerHelper;
import main.fr.esgi.kiosk.helpers.UIHelper;
import main.fr.esgi.kiosk.models.Cart;
import main.fr.esgi.kiosk.models.Meal;
import main.fr.esgi.kiosk.models.RessourceElementProduct;
import main.fr.esgi.kiosk.models.ui.CartElementUI;
import main.fr.esgi.kiosk.views.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class FocusProductElementController<T extends RessourceElementProduct>  implements FxmlController{

    @FXML
    private HBox focusProductContainer;

    @FXML
    private Label productElementName;

    @FXML
    private Label productElementPrice;

    @FXML
    private VBox optionsContainer;

    @FXML
    private ImageView productElementImage;

    private final StageManagerHelper stageManagerHelper;

    private T selectedProductElement;
    private CommandController<RessourceElementProduct> commandController;

    private Cart<T> cart;

    @Autowired
    @Lazy
    public FocusProductElementController(StageManagerHelper stageManagerHelper, CommandController<RessourceElementProduct> commandController, Cart<T> cart){
        this.stageManagerHelper = stageManagerHelper;
        this.commandController = commandController;
        this.cart = cart;

    }

    public void setSelectedProductElement(T selectedProductElement) {
        this.selectedProductElement = selectedProductElement;
    }

    @Override
    public void initialize() {

        if(selectedProductElement instanceof Meal){

            productElementName.setText(selectedProductElement.getName());
            productElementPrice.setText(String.valueOf(selectedProductElement.getPrice() + " €"));
            productElementImage.setImage(selectedProductElement.getImage());
        }

    }

    @FXML
    void commandHome(){
        UIHelper.makeFadeOutTransition(focusProductContainer, stageManagerHelper, FxmlView.COMMAND_HOME);

    }

    @FXML
    void addToCart(){
        // TODO: Add to cart

        cart.add(selectedProductElement);
        commandHome();
    }
}
