package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.fr.esgi.kiosk.helpers.StageManagerHelper;
import main.fr.esgi.kiosk.views.FxmlView;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main extends Application {

    private ConfigurableApplicationContext springContext;
    private StageManagerHelper stageManagerHelper;


    /**
     * Useful to override this method by sub-classes wishing to change the first
     * Scene to be displayed on startup. Example: Functional tests on main
     * window.
     */
    protected void displayInitialScene() {
        stageManagerHelper.switchScene(FxmlView.SPLASH_SCREEN);
    }

    public static void main(String[] args){

        launch(args);
    }

    @Override
    public void init() {
        springContext = bootstrapSpringApplicationContext();
    }

    @Override
    public void start(Stage stage){

        stage.setMinHeight(1492);
        stage.setMinWidth(1800);
        stage.setMaxHeight(stage.getMinHeight());
        stage.setMaxWidth(stage.getMinWidth()+30);
        
        stageManagerHelper = springContext.getBean(StageManagerHelper.class, stage);

        displayInitialScene();
    }

    @Override
    public void stop() {
        springContext.close();
    }

    /////////////////////////// PRIVATE ///////////////////////////////////////
    private ConfigurableApplicationContext bootstrapSpringApplicationContext() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
        String[] args = getParameters().getRaw().toArray(new String[0]);
        builder.headless(false); //needed for TestFX integration testing or else will get a java.awt.HeadlessException during tests
        return builder.run(args);
    }
}
