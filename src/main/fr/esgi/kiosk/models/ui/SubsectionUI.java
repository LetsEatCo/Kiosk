package main.fr.esgi.kiosk.models.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import main.fr.esgi.kiosk.controllers.AccompanimentController;
import main.fr.esgi.kiosk.helpers.UIHelper;
import main.fr.esgi.kiosk.models.MealSubsection;

import java.util.ArrayList;

public class SubsectionUI extends VBox {
    private long minSelections;
    private long maxSelections;
    private AccompanimentController accompanimentController;
    private MealSubsection mealSubsection;

    public SubsectionUI(MealSubsection mealSubsection, AccompanimentController accompanimentController) {

        this.accompanimentController = accompanimentController;
        this.mealSubsection = mealSubsection;
        this.minSelections = mealSubsection.getMinSelectionsPermitted();
        this.maxSelections = mealSubsection.getMaxSelectionsPermitted();

        initUI();

    }

    private void initUI() {

        Label subsectionName = new Label(mealSubsection.getName());
        this.getChildren().add(subsectionName);

        ArrayList<OptionMealUI> optionProductsUIArrayList = UIHelper.createProductsElementsOptionsUI(mealSubsection.getProducts(), accompanimentController);
        ArrayList<OptionMealUI> optionIngredientsUIArrayList = UIHelper.createProductsElementsOptionsUI(mealSubsection.getIngredients(), accompanimentController);

        this.getChildren().addAll(optionIngredientsUIArrayList);
        this.getChildren().addAll(optionProductsUIArrayList);
    }
}
