package main.fr.esgi.kiosk.models;

import java.util.ArrayList;

public class MealSubsection extends Ressource {

    private String name;
    private boolean isRequired = false;
    private boolean allowMultipleSelections;
    private int minSelectionsPermitted = 1;
    private int maxSelectionsPermitted = 1;
    private ArrayList<Product> products;
    private ArrayList<Ingredient> ingredients;


}
