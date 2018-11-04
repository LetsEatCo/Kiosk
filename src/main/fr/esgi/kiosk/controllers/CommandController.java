package main.fr.esgi.kiosk.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.fr.esgi.kiosk.helpers.StageManagerHelper;
import main.fr.esgi.kiosk.helpers.UIHelper;
import main.fr.esgi.kiosk.models.Meal;
import main.fr.esgi.kiosk.models.Product;
import main.fr.esgi.kiosk.models.RessourceElementProduct;
import main.fr.esgi.kiosk.models.Store;
import main.fr.esgi.kiosk.models.ui.ElementUI;
import main.fr.esgi.kiosk.views.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class CommandController implements FxmlController {

    private int adminCounter = 0;
    private ArrayList<Product> products;
    private ArrayList<Meal> meals;
    private ArrayList<ElementUI> productElementUIArrayList;
    private ArrayList<ElementUI> mealsElementUIArrayList;
    private Store store;

    @FXML
    private HBox root;

    @FXML
    private Pane mainContent;

    @FXML
    private VBox cartPane;

    private final StageManagerHelper stageManagerHelper;

    @Autowired @Lazy
    public CommandController(StageManagerHelper stageManagerHelper, Store store) {
        this.stageManagerHelper = stageManagerHelper;
        this.store = store;
    }

    @Override
    public void initialize() {

        root.setOpacity(0);
        UIHelper.makeFadeInTransition(root);
        lazyLoadProducts();
    }


    @FXML
    void adminRegistration(ActionEvent event) {

        adminCounter +=1;

        if(adminCounter == 10) {

            adminCounter=0;
            UIHelper.makeFadeOutTransition(root,stageManagerHelper,FxmlView.ADMIN_LOGIN);
        }
    }

    @FXML
    void loadPreviousPage(ActionEvent event) {

        UIHelper.makeFadeOutTransition(root,stageManagerHelper,FxmlView.LOCATION);
    }

    @FXML
    void loadMenu() {

        if(mealsElementUIArrayList != null)loadUIContent(mealsElementUIArrayList);

    }

    @FXML
    void loadProducts() {

        if(productElementUIArrayList != null)loadUIContent(productElementUIArrayList);

    }

    @FXML
    void loadDesserts() {

    }

    public void addProductElement(Object productElement){

        System.out.println(productElement);
        System.out.println(this);
    }

    public static void getProduct(Object productElement) {
        System.out.println(productElement);
    }

    @FXML
    void order(ActionEvent event)  {

        System.out.println("Order process...");

    }

    private <T> void loadUIContent(ArrayList<T> elementUI) {

        VBox vBox = new VBox();

        int size = elementUI.size();
        int productsIndexJourney = 0;
        for(int i =0; i<size; i++){

            HBox hBox = new HBox();

            for(int j=productsIndexJourney;j<elementUI.size();j++){

                hBox.getChildren().add((Node) elementUI.get(productsIndexJourney));
                productsIndexJourney +=1;
            }

            vBox.getChildren().add(hBox);

        }

        mainContent.getChildren().removeAll();
        mainContent.getChildren().setAll(vBox);
    }

    private void lazyLoadProducts() {

        products = store.getSections().get(0).getProducts();
        meals = store.getSections().get(0).getMeals();

        productElementUIArrayList = UIHelper.createProductsElementsUI(products);
        mealsElementUIArrayList = UIHelper.createProductsElementsUI(meals);

    }




}
