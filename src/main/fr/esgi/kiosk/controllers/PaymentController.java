package main.fr.esgi.kiosk.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class PaymentController implements FxmlController {

    @FXML
    private VBox root;

    @FXML
    private JFXTextField cardNumberField;

    @FXML
    private JFXTextField expMonthField;

    @FXML
    private JFXTextField expYearField;

    @FXML
    private JFXTextField cvcField;

    @FXML
    private JFXButton paymentBtn;


    private Order order;
    private final StageManagerHelper stageManagerHelper;
    private final StripeRequestHelper stripeRequestHelper;

    @Autowired
    @Lazy
    public PaymentController(StageManagerHelper stageManagerHelper, StripeRequestHelper stripeRequestHelper, Order order) {
        this.stageManagerHelper = stageManagerHelper;
        this.stripeRequestHelper = stripeRequestHelper;
        this.order = order;
    }

    @Override
    public void initialize() {
        paymentBtn.setText(String.format("Pay %.2f €", order.getTotalToPay()));
    }

    @FXML
    void processPayment() throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {

        String number = cardNumberField.getText();
        String expMonth = expMonthField.getText() ;
        String expYear = expYearField.getText();
        String cvc = cvcField.getText();

        if (number.length() != 16 || expMonth.length()!= 2 ||expYear.length()!=4 || cvc.length()!=3 ){
            //TODO: Add UI error

            return;
        }

        if (!(NumberUtils.isCreatable(number)&&NumberUtils.isCreatable(expMonth)&&NumberUtils.isCreatable(expYear)&&NumberUtils.isCreatable(cvc))){

            return;
        }


        CreditCard card = new CreditCard(number,expMonth,expYear,cvc);

        String token = stripeRequestHelper.requestToken(card);

        PaymentDetails paymentDetails = new PaymentDetails(token);
        order.setPaymentDetails(paymentDetails);

        System.out.println(token);

        //TODO: Send to a success page and after go back home !
    }

    @FXML
    void previousPage(){
        UIHelper.makeFadeOutTransition(root,stageManagerHelper, FxmlView.COMMAND_HOME);
    }


}
