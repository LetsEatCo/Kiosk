package main.fr.esgi.kiosk.spring.config;

import javafx.stage.Stage;
import main.fr.esgi.kiosk.helpers.StageManagerHelper;
import main.fr.esgi.kiosk.models.Order;
import main.fr.esgi.kiosk.models.Store;
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
//    @Bean
//    @Scope("prototype")
//    public ExceptionWriter exceptionWriter() {
//        return new ExceptionWriter(new StringWriter());
//    }

    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("Bundle");
    }

    @Bean
    @Lazy(value = true) //Stage only created after Spring context bootstap
    public StageManagerHelper stageManagerHelper(Stage stage) {
        return new StageManagerHelper(springFXMLLoader, stage);
    }
    @Bean
    public Store store() throws IOException, ParseException {
        StoreRouter storeRouter = new StoreRouter();
        return storeRouter.getStore();
    }

    @Bean
    public Order order(){
        return new Order();
    }

}
