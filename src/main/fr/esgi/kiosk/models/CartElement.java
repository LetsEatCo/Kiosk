package main.fr.esgi.kiosk.models;

import java.util.HashSet;

class CartElement {

    private String mealUuid;
    private String productUuid;
    private int quantity;
    private HashSet<String> optionUuids;


    public <T extends RessourceElementProduct> CartElement(String uuid, int quantity, HashSet<String> optionUuids, T productElement) {


        if(productElement instanceof Meal){
            this.mealUuid = uuid;
        }else this.productUuid = uuid;

        this.quantity = quantity;
        this.optionUuids = optionUuids;

    }
}
