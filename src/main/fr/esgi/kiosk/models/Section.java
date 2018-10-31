package main.fr.esgi.kiosk.models;

import java.util.ArrayList;

public class Section extends Ressource{

    private String name;
    private ArrayList<Product> products;
    private ArrayList<Meal> meals;

    public Section(String name, ArrayList<Meal> meals, ArrayList<Product> products) {
        this.name = name;
        this.products = products;
        this.meals = meals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }
}
