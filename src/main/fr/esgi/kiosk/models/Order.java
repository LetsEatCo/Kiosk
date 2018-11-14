package main.fr.esgi.kiosk.models;


import java.util.ArrayList;

public class Order extends ArrayList<CartElement> {

    private boolean isTakeAway;

    public void setTakeAway(boolean takeAway) {
        isTakeAway = takeAway;
    }

    public <T extends RessourceElementProduct> void initialize(Cart<T> cart){

        for (T productElement : cart) {

            String uuid = productElement.getUuid();
            int quantity = (int)productElement.getQuantity();
            CartElement cartElement = new CartElement(uuid,quantity, productElement.getOptionsUuids());
            this.add(cartElement);

        }

    }
}
