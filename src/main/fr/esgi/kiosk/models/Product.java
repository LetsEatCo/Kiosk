package main.fr.esgi.kiosk.models;

import javafx.scene.image.Image;

public class Product {

    private String name;
    private double price;
    private Image image = new Image("/main/fr/esgi/kiosk/assets/images/chicken-leg.png");

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
