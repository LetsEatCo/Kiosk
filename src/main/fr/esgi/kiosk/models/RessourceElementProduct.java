package main.fr.esgi.kiosk.models;

import javafx.scene.image.Image;

public class RessourceElementProduct {

    protected String uuid;
    protected String name;
    protected double price;

    protected String imageUrl;
    protected Image image = new Image("/main/resources/assets/images/560491.jpg");

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public void setImage() {
        this.image = new Image(getImageUrl());
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
