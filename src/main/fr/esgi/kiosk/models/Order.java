package main.fr.esgi.kiosk.models;

import java.util.ArrayList;

public class Order <T extends RessourceElementProduct>{

    private String placeToEat;
    private ArrayList<T> productsElements;

    public String getPlaceToEat() {
        return placeToEat;
    }

    public void setPlaceToEat(String placeToEat) {
        this.placeToEat = placeToEat;
    }

    public ArrayList<T> getProductsElements() {
        return productsElements;
    }

    public void setProductsElements(ArrayList<T> productsElements) {
        this.productsElements = productsElements;
    }
}
