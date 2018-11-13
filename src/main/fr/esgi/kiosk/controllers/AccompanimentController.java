package main.fr.esgi.kiosk.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jdk.nashorn.internal.ir.annotations.Ignore;
import main.fr.esgi.kiosk.helpers.StageManagerHelper;
import main.fr.esgi.kiosk.helpers.UIHelper;
import main.fr.esgi.kiosk.models.*;
import main.fr.esgi.kiosk.models.ui.OptionMealUI;
import main.fr.esgi.kiosk.models.ui.SectionUI;
import main.fr.esgi.kiosk.models.ui.SubsectionUI;
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

    private StageManagerHelper stageManagerHelper;
    private DrinkController<T> drinkController;

    @Autowired @Lazy
    public AccompanimentController(StageManagerHelper stageManagerHelper, DrinkController<T> drinkController) {
        this.stageManagerHelper = stageManagerHelper;
        this.drinkController = drinkController;
    }

    void setSelectedProductElement(T selectedProductElement) {
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

                    SubsectionUI subsectionUI = new SubsectionUI(mealSubsection, this);
                    initOptions(subsectionUI);

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

    private void initOptions(SubsectionUI subsectionUI){
        UIHelper.loadUIContent(subsectionUI, optionsContainer);
    }

}
