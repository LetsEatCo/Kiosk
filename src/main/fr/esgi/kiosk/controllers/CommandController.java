package main.fr.esgi.kiosk.controllers;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.fr.esgi.kiosk.helpers.StageManagerHelper;
import main.fr.esgi.kiosk.helpers.UIHelper;
import main.fr.esgi.kiosk.models.*;
import main.fr.esgi.kiosk.models.ui.CartElementUI;
import main.fr.esgi.kiosk.models.ui.ElementUI;
import main.fr.esgi.kiosk.models.ui.SectionUI;
import main.fr.esgi.kiosk.views.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CommandController <T extends RessourceElementProduct>  implements FxmlController {

    @FXML
    private VBox sectionsContainer;
    private int adminCounter = 0;
    private Store store;
    private Order<T> order;

    @FXML
    private HBox root;

    @FXML
    private Pane mainContent;

    @FXML
    private VBox cartPane;

    private final StageManagerHelper stageManagerHelper;
    private ProductCompositionController<T> accompanimentController;
    private Cart<T> cart;
    private ElementUI<RessourceElementProduct> test;

    @Autowired @Lazy
    public CommandController(StageManagerHelper stageManagerHelper, Store store, Order<T> order, ProductCompositionController<T> accompanimentController, Cart<T> cart) {
        this.stageManagerHelper = stageManagerHelper;
        this.store = store;
        this.order = order;
        this.accompanimentController = accompanimentController;
        this.cart = cart;
    }

    @Override
    public void initialize() {

        root.setOpacity(0);
        UIHelper.makeFadeInTransition(root);
        lazyLoadProducts();
        loadCartElement(cart);
    }


    @FXML
    void adminRegistration() {

        adminCounter +=1;

        if(adminCounter == 10) {

            adminCounter=0;
            UIHelper.makeFadeOutTransition(root,stageManagerHelper,FxmlView.ADMIN_LOGIN);
        }
    }

    @FXML
    void loadPreviousPage() {

        UIHelper.makeFadeOutTransition(root,stageManagerHelper,FxmlView.LOCATION);
    }

    public void focusProductElement(T productElement) {

        accompanimentController.setSelectedProductElement(productElement);
        stageManagerHelper.switchScene(FxmlView.ACCOMPANIMENT);

    }

    @FXML
    void order()  {

        System.out.println("Order process...");

    }

    private void loadCartElement(Cart<T> cart){

        for(T productElement : cart){

            CartElementUI<T> cartElementUI = new CartElementUI<>(productElement, this);
            cartPane.getChildren().add(cartElementUI);

        }

    }

    public void createUIElements(Section section){


        ArrayList<ElementUI> elementUIS = new ArrayList<>();
        ArrayList<ElementUI> mealsElementUI  = UIHelper.createProductsElementsUI(section.getMeals());
        ArrayList<ElementUI> productsElementUI  = UIHelper.createProductsElementsUI(section.getProducts());

        elementUIS.addAll(mealsElementUI);
        elementUIS.addAll(productsElementUI);

        loadUIContent(elementUIS, mainContent);


    }

    public void addSections(SectionUI sectionUI){
        sectionsContainer.getChildren().add(sectionUI);

    }

    public void removeProductElementToCart(T productElement, CartElementUI cartElementUI){

        cart.remove(productElement);
        cartPane.getChildren().remove(cartElementUI);

//        loadCartElement(cart);

    }

    private <T> void loadUIContent(ArrayList<T> elementUI, Pane content) {

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


        content.getChildren().removeAll();
        content.getChildren().setAll(vBox);
    }

    private void lazyLoadProducts() {

        Sections sections = store.getSections();

        for (Section section : sections) {

            new SectionUI(section, this);

        }

    }




}
