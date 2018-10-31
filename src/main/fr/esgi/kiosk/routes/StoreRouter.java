package main.fr.esgi.kiosk.routes;

import main.fr.esgi.kiosk.helpers.CredentialsHelper;
import main.fr.esgi.kiosk.helpers.HttpHelper;
import main.fr.esgi.kiosk.helpers.JsonHelper;
import main.fr.esgi.kiosk.models.*;
import org.apache.http.NameValuePair;
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

        List<NameValuePair> credentials = new ArrayList<>();
        credentials.add(new BasicNameValuePair("email", email));
        credentials.add(new BasicNameValuePair("password", password));

        JSONObject jsonObject = (JSONObject) HttpHelper.httpPostRequest(routes.getProperty("login"), credentials);

        if( jsonObject.get("jwt") instanceof String && jsonObject.get("uuid") instanceof String){

            StoreCredentials storeCredentials = new StoreCredentials((String) jsonObject.get("jwt"), (String) jsonObject.get("uuid"));
            credentialsHelper.createCredentials(storeCredentials);
        }

    }

    public JSONArray getProducts() throws IOException, ParseException {

        CredentialsHelper credentialsHelper = new CredentialsHelper();
        Properties routes = credentialsHelper.getRoutes();
        Properties config = credentialsHelper.getStoreCredentials();

        String route = routes.getProperty("getProducts");
        String jwt = config.getProperty("jwt");

        if(HttpHelper.httpGetRequest(route, jwt) instanceof JSONArray){

           return (JSONArray) HttpHelper.httpGetRequest(route, jwt);
        }

        return null ;
    }

    public Store getStore() throws IOException, ParseException {

        CredentialsHelper credentialsHelper = new CredentialsHelper();
        Properties routes = credentialsHelper.getRoutes();
        Properties config = credentialsHelper.getStoreCredentials();

        String storeUuid = config.getProperty("uuid");
        String route = routes.getProperty("getStore") + storeUuid;


        if(HttpHelper.httpGetRequest(route) instanceof JSONObject){

            JSONObject storeJson = (JSONObject) HttpHelper.httpGetRequest(route);


            String uuid = (String)storeJson.get("uuid");
            String name = (String) storeJson.get("name");
            String email = (String) storeJson.get("email");
            String phoneNumber = (String) storeJson.get("phoneNumber");
            String imageUrl = (String)storeJson.get("imageUrl");

            if(storeJson.get("sections") instanceof JSONArray){

                JSONArray sectionsJson = (JSONArray) storeJson.get("sections");
                Sections sections = new Sections();

                for (Object section : sectionsJson) {

                    if(section instanceof JSONObject){

                        if(((JSONObject) section).get("meals") instanceof JSONArray && ((JSONObject) section).get("products") instanceof JSONArray){

                            String sectionName = (String) ((JSONObject) section).get("name");
                            JSONArray mealsJson = (JSONArray) ((JSONObject) section).get("meals");
                            ArrayList<Meal> meals = JsonHelper.parseJsonMeals(mealsJson);

                            JSONArray productsJson = (JSONArray) ((JSONObject) section).get("products");
                            ArrayList<Product> products = JsonHelper.parseJsonProducts(productsJson);

                            Section realSection = new Section(sectionName,meals,products);
                            sections.add(realSection);

                        }

                    }

                }


            return new Store(uuid,name,email,phoneNumber, imageUrl, sections);

            }


        }

        return null;
    }


}
