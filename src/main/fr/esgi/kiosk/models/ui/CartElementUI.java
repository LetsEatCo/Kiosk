package main.fr.esgi.kiosk.models.ui;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.fr.esgi.kiosk.helpers.CredentialsHelper;
import main.fr.esgi.kiosk.helpers.UIHelper;
import main.fr.esgi.kiosk.models.Product;
import main.fr.esgi.kiosk.models.RessourceElementProduct;

import java.io.IOException;
import java.util.Properties;

public class CartElementUI<T extends RessourceElementProduct> extends Parent {

    private T productElement;
    private int quantity = 1;
    private static final String ADD_QUANTITY_IMAGE = "/main/resources/assets/images/add.png";
    private static final String DECREASE_QUANTITY_IMAGE = "/main/resources/assets/images/delete.png";

    public CartElementUI(T productElement) {

        this.productElement = productElement;

        HBox mainContainer = new HBox();

        // Setting Sizes and elements position
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.prefWidth(325);
        mainContainer.prefHeight(90);

        ImageView productElementImage= new ImageView(productElement.getImage());

        productElementImage.setFitWidth(90);
        productElementImage.setFitHeight(90);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.prefWidth(150);
        vBox.prefHeight(90);
        vBox.minWidth(vBox.getPrefWidth());
        vBox.minHeight(vBox.getPrefHeight());

        Label productName = new Label(productElement.getName());
        Label quantityLabel = new Label(String.valueOf(quantity));
        Label productElementPrice = new Label(String.valueOf(productElement.getPrice() + " €"));

        productName.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20");
        quantityLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20");
        productElementPrice.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 20");

        vBox.getChildren().addAll(productName, quantityLabel, productElementPrice);

        HBox btnContainer = new HBox();
        btnContainer.setAlignment(Pos.CENTER);
        btnContainer.prefHeight(90);
        btnContainer.prefWidth(120);
        btnContainer.minHeight(btnContainer.getPrefHeight());
        btnContainer.minWidth(btnContainer.getPrefWidth());

        JFXButton decreaseQuantity = new JFXButton();
        ImageView decreaseImage = new ImageView(new Image(DECREASE_QUANTITY_IMAGE));
        decreaseImage.setFitHeight(30);
        decreaseImage.setFitWidth(30);
        decreaseQuantity.setGraphic(decreaseImage);

        JFXButton increaseQuantity = new JFXButton();
        ImageView increaseImage = new ImageView(new Image(ADD_QUANTITY_IMAGE));
        increaseImage.setFitHeight(30);
        increaseImage.setFitWidth(50);
        increaseQuantity.setGraphic(increaseImage);

        btnContainer.getChildren().addAll(decreaseQuantity, increaseQuantity);

        mainContainer.getChildren().addAll(productElementImage, vBox, btnContainer);

        this.getChildren().add(mainContainer);

    }

}
