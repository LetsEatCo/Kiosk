package main.fr.esgi.kiosk.models.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import main.fr.esgi.kiosk.controllers.CommandController;
import main.fr.esgi.kiosk.models.RessourceElementProduct;

public class OptionMealUI<T extends RessourceElementProduct> extends Parent {

    private long quantity = 1;
    private double price;
    private String checkBoxLabel;

    private HBox container;
    private JFXCheckBox checkBox;

    private Object controller;

    public OptionMealUI(T productElement, Object controller) {

        this.controller = controller;
        this.price = productElement.getPrice();
        this.checkBoxLabel = productElement.getName() + " ( + " + price + " € )";

        checkBox = new JFXCheckBox();
        container = new HBox();

        container.setAlignment(Pos.CENTER);

        container.setSpacing(30);

        checkBox.setText(checkBoxLabel);
        checkBox.setStyle("-fx-font-size: 30; -fx-font-family: Roboto");
        checkBox.setOnAction(event -> {
            checkBox.setSelected(false);
        });

        container.getChildren().addAll(checkBox);

        this.getChildren().removeAll();
        this.getChildren().addAll(container);
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCheckBoxLabel() {
        return checkBoxLabel;
    }

    public void setCheckBoxLabel(String checkBoxLabel) {
        this.checkBoxLabel = checkBoxLabel;
    }
}
