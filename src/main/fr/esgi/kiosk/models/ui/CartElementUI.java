package main.fr.esgi.kiosk.models.ui;

import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import main.fr.esgi.kiosk.helpers.CredentialsHelper;
import main.fr.esgi.kiosk.helpers.UIHelper;

import java.io.IOException;
import java.util.Properties;

public class CartElementUI extends HBox {



    public CartElementUI() throws IOException {

        CredentialsHelper credentialsHelper = new CredentialsHelper();
        Properties properties = credentialsHelper.getViews();
        Parent fxml = UIHelper.loadFxml(properties.getProperty("cartElement"));
        this.getChildren().setAll(fxml);

    }

}
