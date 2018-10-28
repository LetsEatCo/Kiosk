package main.fr.esgi.kiosk.models.ui;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.fr.esgi.kiosk.helpers.CredentialsHelper;
import main.fr.esgi.kiosk.helpers.UIHelper;
import main.fr.esgi.kiosk.models.Product;

import java.io.IOException;
import java.util.Properties;

public class ProductElementUI extends Parent {

    private String productName;
    private String price;
    private ImageView imageView;
    private Product product;

    public ProductElementUI(Product product) throws IOException {

        CredentialsHelper credentialsHelper = new CredentialsHelper();
        Properties properties = credentialsHelper.getViews();
        Parent fxml = UIHelper.loadFxml(properties.getProperty("productElement"));
        this.getChildren().removeAll();
        this.getChildren().setAll(fxml);

        this.product = product;

        Pane pane = (Pane)this.getChildren().get(0);
        JFXButton jfxButton = (JFXButton)pane.getChildren().get(0);

        if(jfxButton.getGraphic() instanceof  VBox){

            VBox vBox = (VBox) jfxButton.getGraphic();

            if(vBox.getChildren().get(0) instanceof ImageView){
                ImageView imageView = ((ImageView)vBox.getChildren().get(0));
                imageView.setImage(product.getImage());
            }


            if(vBox.getChildren().get(1) instanceof Label){

                Label productNameLabel = (Label)vBox.getChildren().get(1);
                productNameLabel.setText(product.getName());
            }

            if(vBox.getChildren().get(2) instanceof Label){

                ((Label)vBox.getChildren().get(2)).setText(String.valueOf(product.getPrice()));
            }
        }

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
