package main.fr.esgi.kiosk.spring.config;


import javafx.stage.Stage;
import main.fr.esgi.kiosk.helpers.StageManagerHelper;
import main.fr.esgi.kiosk.models.*;
import main.fr.esgi.kiosk.routes.StoreRouter;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;
import java.util.ResourceBundle;

@Configuration
public class AppJavaConfig {
    @Autowired
    SpringFXMLLoader springFXMLLoader;

    /**
     * Useful when dumping stack trace to a string for logging.
     * @return ExceptionWriter contains logging utility methods
     */

    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("Bundle");
    }

    @Bean
    @Lazy //Stage only created after Spring context bootstrap
    public StageManagerHelper stageManagerHelper(Stage stage) {
        return new StageManagerHelper(springFXMLLoader, stage);
    }
    @Bean
    public Store store() throws IOException, ParseException {
        StoreRouter storeRouter = new StoreRouter();
        return storeRouter.getStore();
    }
    @Bean
    public Order order() throws IOException, ParseException {

        return new Order(store().getUuid());
    }

    @Bean
    public Cart cart(){

        return new Cart();
    }

}
