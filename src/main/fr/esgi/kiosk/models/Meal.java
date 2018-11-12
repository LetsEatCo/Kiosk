package main.fr.esgi.kiosk.models;

import javafx.scene.image.Image;

public class Meal extends RessourceElementProduct{

    private String reference;
    private int productQuantity;
//    private Product product;
    private Subsections subsections;

    public Meal(){
        /*
        * Voluntary empty Constructor for dependency injection*/
    }

    public Meal(String uuid, String reference, String name, double price, int productQuantity) {
        this.uuid = uuid;
        this.reference = reference;
        this.name = name;
        this.price = price;
        this.productQuantity = productQuantity;
//        this.product = product;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Image getImage() {
        return image;
    }

    public Subsections getSubsections() {
        return subsections;
    }

    public void setSubsections(Subsections subsections) {
        this.subsections = subsections;
    }
}
