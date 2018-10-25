package main.fr.esgi.kiosk.models.ui;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.fr.esgi.kiosk.helpers.CredentialsHelper;
import main.fr.esgi.kiosk.helpers.UIHelper;

import java.io.IOException;
import java.util.Properties;

public class ProductElementUI extends Parent {

    private String productName;
    private String price;
    private ImageView imageView;

    public ProductElementUI() throws IOException {

        CredentialsHelper credentialsHelper = new CredentialsHelper();
        Properties properties = credentialsHelper.getViews();
        Parent fxml = UIHelper.loadFxml(properties.getProperty("productElement"));
        this.getChildren().setAll(fxml);
    }

    public ProductElementUI(String productName, String price, Image image) throws IOException {

        CredentialsHelper credentialsHelper = new CredentialsHelper();
        Properties properties = credentialsHelper.getViews();
        Parent fxml = UIHelper.loadFxml(properties.getProperty("productElement"));
        this.getChildren().removeAll();
        this.getChildren().setAll(fxml);

        Pane pane = (Pane)this.getChildren().get(0);
        JFXButton jfxButton = (JFXButton)pane.getChildren().get(0);

        System.out.println(jfxButton.getGraphic());

        if(jfxButton.getGraphic() instanceof  VBox){

            VBox vBox = (VBox) jfxButton.getGraphic();

            if(vBox.getChildren().get(0) instanceof ImageView){
                ImageView imageView = ((ImageView)vBox.getChildren().get(0));
                imageView.setImage(image);
            }


            if(vBox.getChildren().get(1) instanceof Label){

                Label productNameLabel = (Label)vBox.getChildren().get(1);
                productNameLabel.setText(productName);
            }

            if(vBox.getChildren().get(2) instanceof Label){

                ((Label)vBox.getChildren().get(2)).setText(price);
            }
        }

    }

    public void setCredentials(){
        Pane pane = (Pane)this.getChildren().get(0);
        JFXButton jfxButton = (JFXButton)pane.getChildren().get(0);
        VBox vBox = (VBox) jfxButton.getChildrenUnmodifiable().get(0);

        ImageView imageView1 = (ImageView)vBox.getChildren().get(0);
        Label productNameLabel = (Label)vBox.getChildren().get(1);
        Label price= (Label)vBox.getChildren().get(2);

        imageView = imageView1;
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
