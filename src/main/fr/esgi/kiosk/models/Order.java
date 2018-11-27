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
    private ArrayList<CartElement> cart = new ArrayList<>();


    public void setTakeAway(boolean takeAway) {
        isTakeAway = takeAway;
    }

    public void setEatIn(boolean eatIn) {
        isEatIn = eatIn;
    }

    public void setStoreUuid(String storeUuid) {
        this.storeUuid = storeUuid;
    }

    public <T extends RessourceElementProduct> void process(Cart<T> cart) {

        for (T productElement : cart) {

            String uuid = productElement.getUuid();
            int quantity = (int)productElement.getQuantity();
            CartElement cartElement = new CartElement(uuid,quantity, productElement.getOptionsUuids(), productElement);
            this.cart.add(cartElement);

        }

        processOrder();
    }

    private void processOrder()  {

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
