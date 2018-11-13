package main.fr.esgi.kiosk.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.fr.esgi.kiosk.helpers.StageManagerHelper;
import main.fr.esgi.kiosk.helpers.UIHelper;
import main.fr.esgi.kiosk.models.*;
import main.fr.esgi.kiosk.models.ui.OptionMealUI;
import main.fr.esgi.kiosk.views.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AccompanimentController<T extends RessourceElementProduct> implements FxmlController{

    @FXML
    private HBox root;

    @FXML
    private VBox rootOptions;

    @FXML
    private VBox optionsContainer;

    @FXML
    private Label subsectionName;

    private Cart<T> cart;
    private T selectedProductElement;

    private long maxSelections ;

    private StageManagerHelper stageManagerHelper;
    private DrinkController<T> drinkController;

    @Autowired @Lazy
    public AccompanimentController(StageManagerHelper stageManagerHelper, DrinkController<T> drinkController) {
        this.stageManagerHelper = stageManagerHelper;
        this.drinkController = drinkController;
    }

    public void setSelectedProductElement(T selectedProductElement) {
        this.selectedProductElement = selectedProductElement;
    }

    @Override
    public void initialize() {
        UIHelper.makeFadeInTransition(root);

        if (selectedProductElement instanceof Meal){

            if (((Meal) selectedProductElement).getSubsections() != null){

                Subsections subsections = ((Meal) selectedProductElement).getSubsections();
                MealSubsection subsection = subsections.get(0);
                subsectionName.setText(subsection.getName());

                for (MealSubsection mealSubsection : subsections) {

                    initOptions(mealSubsection);

                }
            }
        }

    }

    @FXML
    void drinks(){
        drinkController.setSelectedProductElement(selectedProductElement);
        UIHelper.makeFadeOutTransition(root, stageManagerHelper, FxmlView.DRINKS);
    }

    @FXML
    void cancel(){
        UIHelper.makeFadeOutTransition(root, stageManagerHelper, FxmlView.COMMAND_HOME);
    }

    void initOptions(MealSubsection subsection){

        ArrayList<Product> productsOptions = subsection.getProducts();
        ArrayList<Ingredient> ingredientsOptions = subsection.getIngredients();

        ArrayList<OptionMealUI> optionProductsUIArrayList = UIHelper.createProductsElementsOptionsUI(productsOptions, this);
        ArrayList<OptionMealUI> optionIngredientsUIArrayList = UIHelper.createProductsElementsOptionsUI(ingredientsOptions, this);

        UIHelper.loadUIContent(optionProductsUIArrayList, optionsContainer);
        UIHelper.loadUIContent(optionIngredientsUIArrayList, optionsContainer);
    }

}
