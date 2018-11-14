package main.fr.esgi.kiosk.models.ui;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import main.fr.esgi.kiosk.controllers.CommandController;
import main.fr.esgi.kiosk.models.RessourceElementProduct;

import java.io.IOException;

public class ElementUI<T extends RessourceElementProduct> extends Parent{

    private T element;
    private final CommandController commandController;

    public ElementUI(T element, CommandController commandController) {

        this.commandController = commandController;
        this.element = element;

        JFXButton uiBtn = new JFXButton();
        uiBtn.setStyle("-fx-background-color: transparent;-fx-font-family: 'Secular One'");
        VBox uiVbox = new VBox();
        ImageView uiImageView = new ImageView();
        Label uiLabelProductName = new Label(element.getName());
        Label uiLabelProductPrice= new Label(String.format("%.2f", element.getPrice()) + " €");

        String labelCss = "-fx-font-family: 'Secular One'; -fx-font-weight: bold; -fx-font-size: 20";
        uiLabelProductName.setStyle(labelCss);
        uiLabelProductPrice.setStyle(labelCss);

        // Settings UI credentials

        uiImageView.setImage(element.getImage());


        uiVbox.setStyle("-fx-background-color: transparent;-fx-text-fill: black");

        uiVbox.getChildren().addAll(uiImageView, uiLabelProductName, uiLabelProductPrice);

        // Settings Size properties

        uiBtn.prefHeight(237);
        uiBtn.prefWidth(319);

        uiBtn.minHeight(uiBtn.getPrefHeight());
        uiBtn.minHeight(uiBtn.getPrefWidth());
        uiBtn.maxHeight(uiBtn.getPrefHeight());
        uiBtn.maxWidth(uiBtn.getPrefWidth());

        uiImageView.prefHeight(150);
        uiImageView.prefWidth(200);
        uiImageView.setFitWidth(200);
        uiImageView.setFitHeight(150);

        uiVbox.prefHeight(200);
        uiVbox.prefWidth(300);

        uiBtn.setGraphic(uiVbox);

        uiBtn.setOnAction(event -> this.commandController.focusProductElement(this.element));

        this.getChildren().removeAll();
        this.getChildren().setAll(uiBtn);
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }
}
