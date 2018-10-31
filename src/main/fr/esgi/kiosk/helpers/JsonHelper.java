package main.fr.esgi.kiosk.helpers;

import main.fr.esgi.kiosk.models.Meal;
import main.fr.esgi.kiosk.models.Product;
import org.apache.http.HttpResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class JsonHelper {


    public JsonObject getDataFromUrl(String url) throws IOException {

        URL urlObject = new URL(url);

        try(InputStream is = urlObject.openStream();
            JsonReader rdr = Json.createReader(is)){

            JsonObject obj = rdr.readObject();

            return obj;
        }

    }

    public static Object parseJsonData(String json) throws ParseException {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject)jsonParser.parse(json);

        return jsonObject.get("data");
    }

    public static Object getResponseBody(HttpResponse httpResponse) throws IOException, ParseException {


        InputStream inputStream = httpResponse.getEntity().getContent();

        InputStreamReader bufferedInputStream = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(bufferedInputStream);

        StringBuilder stringBuilder = new StringBuilder();
        String tmp;
        while ((tmp = bufferedReader.readLine()) != null){

            stringBuilder.append(tmp);
        }

        return parseJsonData(stringBuilder.toString());
    }

    public static ArrayList<Product> parseJsonProducts(JSONArray products){

        ArrayList<Product> productArrayList = new ArrayList<>();

        for(Object jsonProduct : products){

            if( jsonProduct instanceof JSONObject){

                String uuid = (String) ((JSONObject) jsonProduct).get("uuid");
                String name = (String) ((JSONObject) jsonProduct).get("name");
                double price = Double.valueOf(String.valueOf(((JSONObject) jsonProduct).get("price")));

                Product product = new Product(uuid,name, price);

                productArrayList.add(product);
            }

        }

        return productArrayList;
    }

    public static ArrayList<Meal> parseJsonMeals(JSONArray meals){

        ArrayList<Meal> mealArrayList = new ArrayList<>();

        for(Object jsonMeal : meals){

            if( jsonMeal instanceof JSONObject && ((JSONObject) jsonMeal).get("product") instanceof JSONObject){

                // Setting Meal Entity

                String uuid = (String) ((JSONObject) jsonMeal).get("uuid");
                String reference = (String) ((JSONObject) jsonMeal).get("reference");
                String name = (String) ((JSONObject) jsonMeal).get("name");
                double price = Double.valueOf(String.valueOf(((JSONObject) jsonMeal).get("price")));
                int productQuantity = Integer.valueOf(String.valueOf(((JSONObject) jsonMeal).get("productQuantity")));

                // Setting related Product

                JSONObject jsonProduct = (JSONObject) ((JSONObject) jsonMeal).get("product");

                String productUuid = (String) jsonProduct.get("uuid");
                String productName = (String) jsonProduct.get("name");
                double productPrice = Double.valueOf((String) jsonProduct.get("price"));

                Product product = new Product(productUuid,productName, productPrice);

                Meal meal = new Meal(uuid, reference, name, price, productQuantity, product);

                // TODO: load image product
                meal.setImageUrl("/main/fr/esgi/kiosk/assets/images/370872.jpg");
                meal.setImage();

                // TODO : add subsections

                mealArrayList.add(meal);

            }

        }

        return mealArrayList;
    }


}
