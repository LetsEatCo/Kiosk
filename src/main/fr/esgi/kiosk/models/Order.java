package main.fr.esgi.kiosk.models;


import com.google.gson.Gson;
import main.fr.esgi.kiosk.helpers.CredentialsHelper;
import main.fr.esgi.kiosk.helpers.HttpHelper;
import org.apache.http.entity.StringEntity;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Order{

    private boolean isTakeAway = false;
    private boolean isEatIn = false;
    private String storeUuid;
    private PaymentDetails paymentDetails;
    private double totalToPay = 0;
    private ArrayList<CartElement> cart = new ArrayList<>();

    public Order(String storeUuid) {
        this.storeUuid = storeUuid;
    }

    public void setTakeAway(boolean isTakeAway) {
        this.isTakeAway = isTakeAway;
    }

    public void setEatIn(boolean isEatIn) {
        this.isEatIn = isEatIn;
    }

    public double getTotalToPay() {
        return totalToPay;
    }

    public <T extends RessourceElementProduct> void convertCart(Cart<T> cart) {

        double total = 0;
        for (T productElement : cart) {

            total += (productElement.getPrice() + productElement.totalOptionsPrice)*productElement.getQuantity();
            String uuid = productElement.getUuid();
            int quantity = (int)productElement.getQuantity();
            CartElement cartElement = new CartElement(uuid,quantity, productElement.getOptionsUuids(), productElement);
            this.cart.add(cartElement);

        }
        totalToPay = total;

    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public void processOrder() {

        Gson gson = new Gson();
        StringEntity params;
        try {
            CredentialsHelper credentialsHelper = new CredentialsHelper();
            Properties routes = credentialsHelper.getRoutes();
            String orderUrl = routes.getProperty("order");
            params = new StringEntity(gson.toJson(this));
            params.setContentType("application/json");
            HttpHelper.httpPostRequest(orderUrl, params);
            this.cart.clear();

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }
}
