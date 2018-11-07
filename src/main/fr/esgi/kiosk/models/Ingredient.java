package main.fr.esgi.kiosk.models;

public class Ingredient extends  Ressource{

    private long quantity;
    double price = 0;

    public Ingredient(String uuid,int quantity) {
        this.uuid = uuid;
        this.quantity = quantity;
    }
    public Ingredient(String uuid,long quantity, double price) {
        this.uuid = uuid;
        this.quantity = quantity;
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
