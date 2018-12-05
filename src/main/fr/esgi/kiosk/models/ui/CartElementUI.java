package main.fr.esgi.kiosk.models.ui;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.fr.esgi.kiosk.controllers.CommandController;
import main.fr.esgi.kiosk.helpers.UIHelper;
import main.fr.esgi.kiosk.models.Meal;
import main.fr.esgi.kiosk.models.RessourceElementProduct;
import main.fr.esgi.kiosk.views.FxmlView;

import java.util.Optional;


public class CartElementUI<T extends RessourceElementProduct> extends Parent {

    private static final String ADD_QUANTITY_IMAGE = "/main/resources/assets/images/add.png";
    private static final String DECREASE_QUANTITY_IMAGE = "/main/resources/assets/images/delete.png";
    private T productElement;
    private Label quantityLabel;
    private Label productElementPrice;
    private CommandController<T> commandController;

    public CartElementUI(T productElement, CommandController<T> commandController) {

        this.commandController = commandController;
        this.productElement = productElement;

        HBox mainContainer = new HBox();

        // Setting Sizes and elements position
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.prefWidth(325);
        mainContainer.prefHeight(90);

        ImageView productElementImage = new ImageView(productElement.getImage());

        productElementImage.setFitWidth(110);
        productElementImage.setFitHeight(90);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.prefWidth(150);
        vBox.prefHeight(90);
        vBox.minWidth(vBox.getPrefWidth());
        vBox.minHeight(vBox.getPrefHeight());

        Label productName = new Label(productElement.getName());
        quantityLabel = new Label(String.valueOf(productElement.getQuantity()));
        productElementPrice = new Label(String.valueOf((productElement.getPrice() + productElement.getTotalOptionsPrice()) * productElement.getQuantity() + " €"));

        String cartCss = "-fx-font-family: 'Secular One';-fx-font-size: 20";

        productName.setStyle(cartCss);
        quantityLabel.setStyle(cartCss);
        productElementPrice.setStyle(cartCss);

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
        increaseImage.setFitWidth(30);
        increaseQuantity.setGraphic(increaseImage);

        // Btn actions

        increaseQuantity.setOnAction(event -> {


            this.productElement.setQuantity(this.productElement.getQuantity() + 1);
            quantityLabel.setText(String.valueOf(this.productElement.getQuantity()));
            productElementPrice.setText(String.format("%.2f €", (this.productElement.getPrice() + this.productElement.getTotalOptionsPrice() )* this.productElement.getQuantity()));


        });

        decreaseQuantity.setOnAction(event -> {

            if (this.productElement.getQuantity() > 1) {
                this.productElement.setQuantity(this.productElement.getQuantity() - 1);
                quantityLabel.setText(String.valueOf(this.productElement.getQuantity()));
                productElementPrice.setText(String.format("%.2f €", (this.productElement.getPrice() + this.productElement.getTotalOptionsPrice() )* this.productElement.getQuantity()));
            }else{


                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Remove Product");
                alert.setHeaderText(null);
                alert.setContentText("Remove Product From Cart");

                Optional<ButtonType> action = alert.showAndWait();

                if(action.get() == ButtonType.OK){

                    if(this.productElement instanceof Meal){

                        (this.productElement).getOptionsUuids().clear();
                    }
                    commandController.removeProductElementToCart(this.productElement, this);
                }


            }

        });

        btnContainer.getChildren().addAll(decreaseQuantity, increaseQuantity);

        mainContainer.getChildren().addAll(productElementImage, vBox, btnContainer);

        this.getChildren().add(mainContainer);

    }

}
