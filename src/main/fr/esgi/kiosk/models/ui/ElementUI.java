package main.fr.esgi.kiosk.models.ui;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import main.fr.esgi.kiosk.controllers.CommandController;
import main.fr.esgi.kiosk.models.RessourceElementProduct;

public class ElementUI<T extends RessourceElementProduct> extends Parent{

    private T element;
    private JFXButton btn;

    public ElementUI(T element) {

//        Parent fxml = UIHelper.loadFxml(properties.getProperty("productElement"));


        this.element = element;

        JFXButton uiBtn = new JFXButton();
        VBox uiVbox = new VBox();
        ImageView uiImageView = new ImageView();
        Label uiLabelProductName = new Label();
        Label uiLabelProductPrice= new Label();

        // Settings UI credentials

        uiImageView.setImage(element.getImage());
        uiLabelProductName.setText(element.getName());
        uiLabelProductPrice.setText(element.getPrice() + " €");

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

        uiBtn.setOnAction(event -> CommandController.getProduct(element));

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
