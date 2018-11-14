package main.fr.esgi.kiosk.models.ui;

import com.jfoenix.controls.JFXCheckBox;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import main.fr.esgi.kiosk.controllers.ProductCompositionController;
import main.fr.esgi.kiosk.models.RessourceElementProduct;

public class OptionMealUI<T extends RessourceElementProduct> extends Parent {

    private double price;
    private String checkBoxLabel;

    private HBox container;
    private JFXCheckBox checkBox;
    private Label supplement;

    private ProductCompositionController controller;
    private SubsectionUI subsectionUI;
    private T productElement;
    private boolean isClicked =false;


    public OptionMealUI(T productElement, ProductCompositionController controller, SubsectionUI subsectionUI) {

        this.controller = controller;
        this.subsectionUI = subsectionUI;
        this.price = productElement.getPrice();
        this.checkBoxLabel = productElement.getName();

        this.supplement = new Label(" ( + " + String.format("%.2f", price) + " € )");
        supplement.getStyleClass().add("option-price");


        checkBox = new JFXCheckBox();
        container = new HBox();
        container.setAlignment(Pos.CENTER_LEFT);

        container.setSpacing(30);

        checkBox.setText(checkBoxLabel);
        checkBox.getStyleClass().add("option-product-element-checkbox");

        checkBox.setOnAction(event -> {


            if((subsectionUI.getCurrentSelections()<subsectionUI.getMaxSelections()) && !isClicked){

                isClicked = true;
                subsectionUI.setCurrentSelections(subsectionUI.getCurrentSelections()+1);
            }
            else if(isClicked){
                isClicked = false;
                subsectionUI.setCurrentSelections(subsectionUI.getCurrentSelections()-1);
            }else{
                checkBox.setSelected(false);
            }
        });

        container.getChildren().addAll(checkBox, supplement);

        this.getChildren().removeAll();
        this.getChildren().addAll(container);
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }
}
