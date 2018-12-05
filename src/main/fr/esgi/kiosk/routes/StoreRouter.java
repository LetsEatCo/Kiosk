package main.fr.esgi.kiosk.routes;

import com.google.gson.Gson;
import main.fr.esgi.kiosk.helpers.CredentialsHelper;
import main.fr.esgi.kiosk.helpers.HttpHelper;
import main.fr.esgi.kiosk.helpers.JsonHelper;
import main.fr.esgi.kiosk.models.*;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class StoreRouter {

    public void login(String email, String password) throws IOException, ParseException {

        CredentialsHelper credentialsHelper = new CredentialsHelper();
        Properties routes = credentialsHelper.getRoutes();

        StoreLogin storeLogin = new StoreLogin(email, password);
        Gson gson = new Gson();
        StringEntity params;

        params = new StringEntity(gson.toJson(storeLogin));
        params.setContentType("application/json");

        JSONObject jsonObject = (JSONObject) HttpHelper.httpPostRequest(routes.getProperty("login"), params);

        Object jwt = jsonObject.get("jwt");
        if( jwt instanceof String){

            Object store = HttpHelper.httpGetRequest(routes.getProperty("me"), (String) jwt);

            String uuid = (String) ((JSONObject)store).get("uuid");
            StoreCredentials storeCredentials = new StoreCredentials((String) jwt, uuid);
            credentialsHelper.createCredentials(storeCredentials);
        }

    }

    public Store getStore() throws IOException, ParseException {

        CredentialsHelper credentialsHelper = new CredentialsHelper();
        Properties routes = credentialsHelper.getRoutes();
        Properties config = credentialsHelper.getStoreCredentials();

        String root = routes.getProperty("getStore");
        String storeUuid = config.getProperty("uuid");
        String route = root + storeUuid;

        Object store = HttpHelper.httpGetRequest(route);


        if(store instanceof JSONObject && ((JSONObject) store).get("sections") instanceof JSONArray){

            JSONObject storeJson = (JSONObject) store;

            String uuid = (String)storeJson.get("uuid");
            String name = (String) storeJson.get("name");
            String email = (String) storeJson.get("email");
            String phoneNumber = (String) storeJson.get("phoneNumber");
            String imageUrl = (String)storeJson.get("imageUrl");


            JSONArray sectionsJson = (JSONArray) storeJson.get("sections");
            Sections sections = new Sections();

            for (Object section : sectionsJson) {

                if(section instanceof JSONObject){

                    if(((JSONObject) section).get("meals") instanceof JSONArray && ((JSONObject) section).get("products") instanceof JSONArray){

                        String sectionName = (String) ((JSONObject) section).get("name");
                        JSONArray mealsJson = (JSONArray) ((JSONObject) section).get("meals");
                        ArrayList<Meal> meals = JsonHelper.parseJsonMeals(mealsJson);

                        JSONArray productsJson = (JSONArray) ((JSONObject) section).get("products");
                        ArrayList<Product> products = JsonHelper.parseJsonProductsIngredients(productsJson);

                        Section realSection = new Section(sectionName,meals,products);
                        sections.add(realSection);

                    }

                }

            }

            return new Store(uuid,name,email,phoneNumber, imageUrl, sections);


        }

        return null;
    }

    public double getVoucher(String voucherCode) throws IOException, ParseException {

        CredentialsHelper credentialsHelper = new CredentialsHelper();
        Properties routes = credentialsHelper.getRoutes();
        Properties config = credentialsHelper.getStoreCredentials();

        String root = routes.getProperty("getVoucher");
        String jwt = config.getProperty("jwt");
        String route = root + voucherCode;

        JSONObject voucher = (JSONObject) HttpHelper.httpGetRequest(route,jwt);

        if (voucher!=null){
            if((boolean)voucher.get("valid"))
                return (double)voucher.get("reduction") ;
        }
        return 0;
    }


}
