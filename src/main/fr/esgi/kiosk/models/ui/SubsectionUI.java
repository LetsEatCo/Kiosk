package main.fr.esgi.kiosk.models.ui;

import main.fr.esgi.kiosk.controllers.AccompanimentController;
import main.fr.esgi.kiosk.helpers.UIHelper;
import main.fr.esgi.kiosk.models.MealSubsection;

import java.util.ArrayList;

public class SubsectionUI extends ArrayList<OptionMealUI> {
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

        ArrayList<OptionMealUI> optionProductsUIArrayList = UIHelper.createProductsElementsOptionsUI(mealSubsection.getProducts(), accompanimentController);
        ArrayList<OptionMealUI> optionIngredientsUIArrayList = UIHelper.createProductsElementsOptionsUI(mealSubsection.getIngredients(), accompanimentController);

        this.addAll(optionIngredientsUIArrayList);
        this.addAll(optionProductsUIArrayList);
    }

    public long getMinSelections() {
        return minSelections;
    }

    public void setMinSelections(long minSelections) {
        this.minSelections = minSelections;
    }

    public long getMaxSelections() {
        return maxSelections;
    }

    public void setMaxSelections(long maxSelections) {
        this.maxSelections = maxSelections;
    }
}
