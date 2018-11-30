package main.fr.esgi.kiosk.helpers;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Token;
import main.fr.esgi.kiosk.models.CreditCard;
import main.fr.esgi.kiosk.models.CreditCardEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class StripeRequestHelper {

    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    @PostConstruct
    public void init(){
        Stripe.apiKey = secretKey;
    }

    public String requestToken(CreditCard card) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {


        Map<String, Object> cardParams = new HashMap<>();
        cardParams.put(CreditCardEnum.NUMBER.value, card.getNumber());
        cardParams.put(CreditCardEnum.EXP_MONTH.value, card.getExpMonth());
        cardParams.put(CreditCardEnum.EXP_YEAR.value, card.getExpYear());
        cardParams.put(CreditCardEnum.CVC.value, card.getCvc());

        String CARD = "card";
        Map<String,Object> tokenParams = new HashMap<>();
        tokenParams.put(CARD, cardParams);

        Token tokenRequest = Token.create(tokenParams);

        return tokenRequest.getId();
    }
}
