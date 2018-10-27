package main.fr.esgi.kiosk.models.ui;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.fr.esgi.kiosk.helpers.CredentialsHelper;
import main.fr.esgi.kiosk.helpers.UIHelper;
import main.fr.esgi.kiosk.models.Product;
import java.io.IOException;
import java.util.Properties;

public class CartElementUI extends Parent {

    private ImageView imageView;
    private Product product;
    private JFXButton closeBtn;
    private JFXButton addBtn;

    public CartElementUI(String productName, double price, int quantity, ImageView imageView) throws IOException {

        // Loading XML Object

        CredentialsHelper credentialsHelper = new CredentialsHelper();
        Properties properties = credentialsHelper.getViews();
        Parent fxml = UIHelper.loadFxml(properties.getProperty("cartElement"));
        this.getChildren().removeAll();
        this.getChildren().setAll(fxml);

        // Set Product Credentials

        product = new Product(productName, price);

        /* Extracting fxml content
        / Check FXML Models Structure
        / The Cart Element contain 3 Childs
        */

        HBox firstChild = (HBox) this.getChildren().get(0);
        VBox secondChild = (VBox) firstChild.getChildren().get(1);



        double height = ((ImageView)firstChild.getChildren().get(0)).getFitHeight();
        double width = ((ImageView)firstChild.getChildren().get(0)).getFitWidth();

        imageView.setFitHeight(height);
        imageView.setFitWidth(width);

        this.imageView = imageView;
        firstChild.getChildren().set(0, imageView);

        Label productNameLabel = (Label)secondChild.getChildren().get(0);
        productNameLabel.setText(productName);

        Label priceLabel= (Label)secondChild.getChildren().get(1);
        priceLabel.setText(String.valueOf(price));

        Label quantityLabel= (Label)secondChild.getChildren().get(2);
        quantityLabel.setText(String.valueOf(quantity));

        HBox thirdChild = (HBox) firstChild.getChildren().get(2);

        closeBtn = (JFXButton)thirdChild.getChildren().get(0);
        addBtn= (JFXButton)thirdChild.getChildren().get(1);

    }

}
