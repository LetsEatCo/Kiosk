package main.fr.esgi.kiosk.models;

public class Ingredient extends  Ressource{

    private String name;
    private int quantity;

    public Ingredient(String uuid,String name, int quantity) {
        this.uuid = uuid;
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
