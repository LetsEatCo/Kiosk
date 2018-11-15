package main.fr.esgi.kiosk.models;

import javafx.scene.image.Image;

import java.util.HashSet;

public class RessourceElementProduct {

    protected String uuid;
    protected String name;
    protected long quantity = 1;
    protected Double price;
    protected String imageUrl;
    protected String description;
    protected Image image;
    protected HashSet<String> optionsUuids = new HashSet<>();

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

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
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

    public HashSet<String> getOptionsUuids() {
        return optionsUuids;
    }

}
