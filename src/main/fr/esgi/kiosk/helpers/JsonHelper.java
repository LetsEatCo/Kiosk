package main.fr.esgi.kiosk.helpers;

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

                String name = (String) ((JSONObject) jsonProduct).get("name");
                double price = Double.valueOf(String.valueOf(((JSONObject) jsonProduct).get("price")));

                Product product = new Product(name, price);

                productArrayList.add(product);
            }

        }

        return productArrayList;
    }


}
