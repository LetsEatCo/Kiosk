package main.fr.esgi.kiosk.models;

import java.util.HashSet;

class CartElement {

    private String uuid;
    private int quantity;
    private HashSet<String> optionUuids;

    CartElement(String uuid, int quantity, HashSet<String> optionUuids) {
        this.uuid = uuid;
        this.quantity = quantity;
        this.optionUuids = optionUuids;
    }
}
