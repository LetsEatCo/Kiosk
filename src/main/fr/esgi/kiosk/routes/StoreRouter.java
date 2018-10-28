package main.fr.esgi.kiosk.routes;

import main.fr.esgi.kiosk.helpers.CredentialsHelper;
import main.fr.esgi.kiosk.helpers.HttpHelper;
import main.fr.esgi.kiosk.models.StoreCredentials;
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

    public JSONObject getStore() throws IOException, ParseException {

        CredentialsHelper credentialsHelper = new CredentialsHelper();
        Properties routes = credentialsHelper.getRoutes();
        Properties config = credentialsHelper.getStoreCredentials();

        String uuid = config.getProperty("uuid");
        String route = routes.getProperty("getStore") + uuid;


        if(HttpHelper.httpGetRequest(route) instanceof JSONObject){

            return (JSONObject) HttpHelper.httpGetRequest(route);
        }

        return null;
    }


}
