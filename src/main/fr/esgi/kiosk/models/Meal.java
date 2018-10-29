package main.fr.esgi.kiosk.models;

import javafx.scene.image.Image;

public class Meal extends RessourceElementProduct{

    private String reference;
    private int productQuantity;
    private Product product;

    public Meal(String uuid, String reference, String name, double price, int productQuantity, Product product) {
        this.uuid = uuid;
        this.reference = reference;
        this.name = name;
        this.price = price;
        this.productQuantity = productQuantity;
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Image getImage() {
        return image;
    }

}
