package main.fr.esgi.kiosk.models.ui;

import com.jfoenix.controls.JFXCheckBox;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import main.fr.esgi.kiosk.controllers.ProductCompositionController;
import main.fr.esgi.kiosk.models.Meal;
import main.fr.esgi.kiosk.models.RessourceElementProduct;

public class OptionMealUI<T extends RessourceElementProduct> extends Parent {

    private JFXCheckBox checkBox;

    private ProductCompositionController<T> controller;
    private T productElement;
    private boolean isClicked =false;
    private String uuid;


    public OptionMealUI(T productElement, ProductCompositionController<T> controller, SubsectionUI subsectionUI) {

        this.productElement = productElement;
        double price = productElement.getPrice();
        String checkBoxLabel = productElement.getName();
        this.uuid = productElement.getUuid();
        Label supplement = new Label(" ( + " + String.format("%.2f", price) + " € )");
        supplement.getStyleClass().add("option-price");


        checkBox = new JFXCheckBox();
        HBox container = new HBox();
        container.setAlignment(Pos.CENTER_LEFT);

        container.setSpacing(30);

        checkBox.setText(checkBoxLabel);
        checkBox.getStyleClass().add("option-product-element-checkbox");

        checkBox.setOnAction(event -> {



                Object controllerCpy = controller.getSelectedProductElement();

                if((subsectionUI.getCurrentSelections()<subsectionUI.getMaxSelections()) && !isClicked){

                    if(controllerCpy instanceof Meal){

                        ((Meal) controllerCpy).getOptionsUuids().add(uuid);
                        isClicked = true;
                        subsectionUI.setCurrentSelections(subsectionUI.getCurrentSelections()+1);
                    }

                }

                else if(isClicked){

                    if(controllerCpy instanceof Meal){

                        ((Meal) controllerCpy).getOptionsUuids().remove(uuid);
                        isClicked = false;
                        subsectionUI.setCurrentSelections(subsectionUI.getCurrentSelections()-1);
                    }
                }else{
                    checkBox.setSelected(false);
                }




        });

        container.getChildren().addAll(checkBox, supplement);

        this.getChildren().removeAll();
        this.getChildren().addAll(container);
    }
}
