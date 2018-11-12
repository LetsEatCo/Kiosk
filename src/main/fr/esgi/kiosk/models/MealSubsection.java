package main.fr.esgi.kiosk.models;

import java.util.ArrayList;

public class MealSubsection extends Ressource {

    private String name;
    private boolean isRequired = false;
    private boolean allowMultipleSelections;
    private long minSelectionsPermitted = 0;
    private long maxSelectionsPermitted = 1;
    private ArrayList<Product> products;
    private ArrayList<Ingredient> ingredients;

    public MealSubsection(String uuid,String name, boolean isRequired, boolean allowMultipleSelections, long minSelectionsPermitted, long maxSelectionsPermitted, ArrayList<Product> products, ArrayList<Ingredient> ingredients) {
        this.uuid = uuid;
        this.name = name;
        this.isRequired = isRequired;
        this.allowMultipleSelections = allowMultipleSelections;
        this.minSelectionsPermitted = minSelectionsPermitted;
        this.maxSelectionsPermitted = maxSelectionsPermitted;
        this.products = products;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public boolean isAllowMultipleSelections() {
        return allowMultipleSelections;
    }

    public void setAllowMultipleSelections(boolean allowMultipleSelections) {
        this.allowMultipleSelections = allowMultipleSelections;
    }

    public long getMinSelectionsPermitted() {
        return minSelectionsPermitted;
    }

    public void setMinSelectionsPermitted(long minSelectionsPermitted) {
        this.minSelectionsPermitted = minSelectionsPermitted;
    }

    public long getMaxSelectionsPermitted() {
        return maxSelectionsPermitted;
    }

    public void setMaxSelectionsPermitted(long maxSelectionsPermitted) {
        this.maxSelectionsPermitted = maxSelectionsPermitted;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
