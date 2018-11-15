package main.fr.esgi.kiosk.models;

import javafx.scene.image.Image;

import java.util.HashSet;
public class Meal extends RessourceElementProduct {

    private String reference;
    private Subsections subsections;

    public Meal() {
        /*
         * Voluntary empty Constructor for dependency injection*/
    }

    public Meal(String uuid, String reference, String name, String description, double price, int productQuantity) {
        this.uuid = uuid;
        this.reference = reference;
        this.name = name;
        this.price = price;
        this.quantity = productQuantity;
        this.description = description;

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
