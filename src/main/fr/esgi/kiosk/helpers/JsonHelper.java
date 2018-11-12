package main.fr.esgi.kiosk.helpers;

import main.fr.esgi.kiosk.models.*;
import main.fr.esgi.kiosk.routes.StoreRouter;
import org.apache.http.HttpResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static main.fr.esgi.kiosk.routes.StoreRouter.*;

public class JsonHelper {

    private final static String PRODUCTS = "products";
    private final static String INGREDIENTS = "ingredients";

    private static Object parseJsonData(String json) throws ParseException {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(json);

        return jsonObject.get("data");
    }

    public static Object getResponseBody(HttpResponse httpResponse) throws IOException, ParseException {


        InputStream inputStream = httpResponse.getEntity().getContent();

        InputStreamReader bufferedInputStream = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(bufferedInputStream);

        StringBuilder stringBuilder = new StringBuilder();
        String tmp;
        while ((tmp = bufferedReader.readLine()) != null) {

            stringBuilder.append(tmp);
        }

        return parseJsonData(stringBuilder.toString());
    }

    public static ArrayList<Product> parseJsonProductsIngredients(JSONArray products) {

        ArrayList<Product> productArrayList = new ArrayList<>();

        for (Object jsonProduct : products) {

            if (jsonProduct instanceof JSONObject) {

                if (((JSONObject) jsonProduct).get("ingredients") instanceof JSONArray) {


                    String uuid = (String) ((JSONObject) jsonProduct).get("uuid");
                    String name = (String) ((JSONObject) jsonProduct).get("name");
                    double price = Double.valueOf(String.valueOf(((JSONObject) jsonProduct).get("price")));

                    JSONArray jsonProductsIngredients = (JSONArray) ((JSONObject) jsonProduct).get("ingredients");

                    Ingredients ingredients = new Ingredients();

                    for (Object jsonProductIngredient : jsonProductsIngredients) {

                        if (jsonProductIngredient instanceof JSONObject) {

                            String ingredientUuid = (String) ((JSONObject) jsonProductIngredient).get("uuid");
                            int ingredientQuantity = (int) ((JSONObject) jsonProductIngredient).get("quantity");
                            Ingredient ingredient = new Ingredient(ingredientUuid, ingredientQuantity);
                            ingredients.add(ingredient);
                        }
                    }
                    Product product = new Product(uuid, name, price, ingredients);

                    productArrayList.add(product);
                }

            }

        }

        return productArrayList;
    }

    private static ArrayList<Product> parseJsonOptionProducts(JSONArray products) {

        ArrayList<Product> productArrayList = new ArrayList<>();

        for (Object jsonProduct : products) {

            if (jsonProduct instanceof JSONObject) {

                Object relatedProduct =  ((JSONObject) jsonProduct).get("product");
                if (relatedProduct instanceof JSONObject) {

                    String uuid = (String) ((JSONObject) jsonProduct).get("uuid");
                    String name = (String) ((JSONObject) relatedProduct).get("name");
                    long quantity = (long) ((JSONObject) jsonProduct).get("quantity");
                    double price = Double.valueOf(String.valueOf(((JSONObject) jsonProduct).get("price")));

                    Product product = new Product(uuid,name,quantity, price);

                    productArrayList.add(product);
                }


            }

        }

        return productArrayList;
    }

    private static ArrayList<Ingredient> parseJsonOptionIngredients(JSONArray ingredients) {

        ArrayList<Ingredient> ingredientsArrayList = new ArrayList<>();

        for (Object jsonIngredient : ingredients) {

            if (jsonIngredient instanceof JSONObject) {

                Object relatedIngredient = ((JSONObject) jsonIngredient).get("ingredient");

                if(relatedIngredient instanceof JSONObject){

                    String uuid = (String) ((JSONObject) jsonIngredient).get("uuid");
                    String name = (String) ((JSONObject) relatedIngredient).get("name");
                    double price = Double.valueOf(String.valueOf(((JSONObject) jsonIngredient).get("price")));
                    long quantity = (long) ((JSONObject) jsonIngredient).get("quantity");

                    Ingredient ingredient = new Ingredient(uuid,name, quantity, price);

                    ingredientsArrayList.add(ingredient);
                }
            }

        }

        return ingredientsArrayList;
    }

    public static ArrayList<Meal> parseJsonMeals(JSONArray meals) {

        ArrayList<Meal> mealArrayList = new ArrayList<>();

        for (Object jsonMeal : meals) {

            if (jsonMeal instanceof JSONObject && ((JSONObject) jsonMeal).get("subsections") instanceof JSONArray) {

                // Setting Meal Entity

                String uuid = (String) ((JSONObject) jsonMeal).get("uuid");
                String reference = (String) ((JSONObject) jsonMeal).get("reference");
                String name = (String) ((JSONObject) jsonMeal).get("name");
                double price = Double.valueOf(String.valueOf(((JSONObject) jsonMeal).get("price")));
                int productQuantity = Integer.valueOf(String.valueOf(((JSONObject) jsonMeal).get("productQuantity")));

                Meal meal = new Meal(uuid, reference, name, price, productQuantity);

                // TODO: load image product
                meal.setImageUrl("/main/resources/assets/images/873086.jpg");
                meal.setImage();

                // TODO : add subsections

                JSONArray jsonSubsections = (JSONArray) ((JSONObject) jsonMeal).get("subsections");
                Subsections subsections = new Subsections();

                /*
                 * Parse each subsection of a meal
                 * */
                for (Object jsonSubsection : jsonSubsections) {

                    if (jsonSubsection instanceof JSONObject) {

                        Object subsectionOptionsJson = ((JSONObject) jsonSubsection).get("options");
                        if (subsectionOptionsJson instanceof JSONObject) {

                            Object productsOptionsJson = ((JSONObject) subsectionOptionsJson).get("products");
                            Object ingredientsOptionsJson = ((JSONObject) subsectionOptionsJson).get("ingredients");

                            if (ingredientsOptionsJson instanceof JSONArray && productsOptionsJson instanceof JSONArray) {


                                String subsectionUuid = (String) ((JSONObject) jsonSubsection).get("uuid");
                                String subsectionName = (String) ((JSONObject) jsonSubsection).get("name");
                                boolean isRequired = (boolean) ((JSONObject) jsonSubsection).get("isRequired");
                                boolean allowMultipleSelections = (boolean) ((JSONObject) jsonSubsection).get("allowMultipleSelections");
                                long minSelectionsPermitted = (long) ((JSONObject) jsonSubsection).get("minSelectionsPermitted");
                                long maxSelectionsPermitted = (long) ((JSONObject) jsonSubsection).get("maxSelectionsPermitted");


                                ArrayList<Product> optionProducts = parseJsonOptionProducts((JSONArray) productsOptionsJson);
                                ArrayList<Ingredient> optionIngredients = parseJsonOptionIngredients((JSONArray) ingredientsOptionsJson);

                                MealSubsection mealSubsection = new MealSubsection(
                                        subsectionUuid,
                                        subsectionName,
                                        isRequired,
                                        allowMultipleSelections,
                                        minSelectionsPermitted,
                                        maxSelectionsPermitted,
                                        optionProducts,
                                        optionIngredients);

                                subsections.add(mealSubsection);
                            }
                        }


                    }

                }
                meal.setSubsections(subsections);
                mealArrayList.add(meal);

            }

        }

        return mealArrayList;
    }


}
