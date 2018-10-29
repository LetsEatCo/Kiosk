package main.fr.esgi.kiosk.models.ui;

import com.jfoenix.controls.JFXButton;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.fr.esgi.kiosk.helpers.CredentialsHelper;
import main.fr.esgi.kiosk.helpers.UIHelper;
import main.fr.esgi.kiosk.models.RessourceElementProduct;

import java.io.IOException;
import java.util.Properties;

public class ElementUI<T extends RessourceElementProduct> extends Parent{

    private T element;
    private JFXButton btn;

    public ElementUI(T element) throws IOException {
        CredentialsHelper credentialsHelper = new CredentialsHelper();
        Properties properties = credentialsHelper.getViews();
        Parent fxml = UIHelper.loadFxml(properties.getProperty("productElement"));
        this.getChildren().removeAll();
        this.getChildren().setAll(fxml);

        this.element = element;

        Pane pane = (Pane)this.getChildren().get(0);
        JFXButton jfxButton = (JFXButton)pane.getChildren().get(0);
        this.btn = jfxButton;

        if(jfxButton.getGraphic() instanceof VBox){

            VBox vBox = (VBox) jfxButton.getGraphic();

            if(vBox.getChildren().get(0) instanceof ImageView){
                ImageView imageView = ((ImageView)vBox.getChildren().get(0));
                imageView.setImage(element.getImage());
            }


            if(vBox.getChildren().get(1) instanceof Label){

                Label productNameLabel = (Label)vBox.getChildren().get(1);
                productNameLabel.setText(element.getName());
            }

            if(vBox.getChildren().get(2) instanceof Label){

                ((Label)vBox.getChildren().get(2)).setText(String.valueOf(element.getPrice()));
            }
        }
    }

    public void onClick(){
        btn.setOnAction(event -> {
            System.out.println("On me!");
        });
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }
}
