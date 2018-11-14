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

    private double price;
    private String checkBoxLabel;

    private HBox container;
    private JFXCheckBox checkBox;
    private Label supplement;

    private ProductCompositionController controller;
    private SubsectionUI subsectionUI;
    private T productElement;
    private boolean isClicked =false;
    private String uuid;


    public OptionMealUI(T productElement, ProductCompositionController controller, SubsectionUI subsectionUI) {

        this.productElement = productElement;
        this.controller = controller;
        this.subsectionUI = subsectionUI;
        this.price = productElement.getPrice();
        this.checkBoxLabel = productElement.getName();
        this.uuid = productElement.getUuid();
        this.supplement = new Label(" ( + " + String.format("%.2f", price) + " € )");
        supplement.getStyleClass().add("option-price");


        checkBox = new JFXCheckBox();
        container = new HBox();
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
                        System.out.println("Option size : " + ((Meal) controllerCpy).getOptionsUuids().size());
                    }

                }

                else if(isClicked){

                    if(controllerCpy instanceof Meal){

                        ((Meal) controllerCpy).getOptionsUuids().remove(uuid);
                        isClicked = false;
                        subsectionUI.setCurrentSelections(subsectionUI.getCurrentSelections()-1);
                        System.out.println("Option size : " + ((Meal) controllerCpy).getOptionsUuids().size());
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
