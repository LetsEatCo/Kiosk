package main.fr.esgi.kiosk.controllers;

import com.stripe.exception.*;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import main.fr.esgi.kiosk.helpers.StageManagerHelper;
import main.fr.esgi.kiosk.helpers.StripeRequestHelper;
import main.fr.esgi.kiosk.helpers.UIHelper;
import main.fr.esgi.kiosk.models.CreditCard;
import main.fr.esgi.kiosk.models.Order;
import main.fr.esgi.kiosk.models.PaymentDetails;
import main.fr.esgi.kiosk.views.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class PaymentRequestScreenController implements FxmlController {

    @FXML
    private VBox splashRoot;

    private CreditCard creditCard;

    private StageManagerHelper stageManagerHelper;

    private final StripeRequestHelper stripeRequestHelper;
    private Order order;

    @Autowired
    @Lazy
    public PaymentRequestScreenController(StageManagerHelper stageManagerHelper, Order order, StripeRequestHelper stripeRequestHelper) {
        this.stageManagerHelper = stageManagerHelper;
        this.order = order;
        this.stripeRequestHelper = stripeRequestHelper;
    }
    @Override
    public void initialize() {
        new PaymentSplashScreen().start();
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    class PaymentSplashScreen extends Thread{


        @Override
        public void run() {
            try {
                Thread.sleep(2000);


                String token = stripeRequestHelper.requestToken(creditCard);

                PaymentDetails paymentDetails = new PaymentDetails(token);
                order.setPaymentDetails(paymentDetails);

//                order.processOrder();

                UIHelper.makeFadeOutTransition(splashRoot, stageManagerHelper, FxmlView.CONFIRM_SCREEN);
            } catch (InterruptedException | CardException | APIException | AuthenticationException | InvalidRequestException | APIConnectionException e) {
                e.printStackTrace();
            }
        }
    }
}
