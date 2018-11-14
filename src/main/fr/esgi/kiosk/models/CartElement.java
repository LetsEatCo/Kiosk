package main.fr.esgi.kiosk.models;

import java.util.HashSet;

public class CartElement {

    private String uuid;
    private int quantity;
    private HashSet<String> optionUuids;

    public CartElement(String uuid, int quantity, HashSet<String> optionUuids) {
        this.uuid = uuid;
        this.quantity = quantity;
        this.optionUuids = optionUuids;
    }
}
