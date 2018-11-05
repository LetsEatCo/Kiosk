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
}
