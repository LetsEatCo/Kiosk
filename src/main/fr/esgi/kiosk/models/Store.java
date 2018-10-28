package main.fr.esgi.kiosk.models;

import java.util.ArrayList;

public class Store extends Ressource {

    private String name;
    private String email;
    private String phoneNumber;
    private String imageUrl;
    private ArrayList<Meal> meals;
    private ArrayList<Product> products;

    public Store(){

    }

    public Store(String uuid, String name, String email, String phoneNumber, String imageUrl, ArrayList<Meal> meals, ArrayList<Product> products) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.imageUrl = imageUrl;
        this.meals = meals;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
