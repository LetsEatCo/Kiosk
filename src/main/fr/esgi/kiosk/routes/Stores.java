package main.fr.esgi.kiosk.routes;

import main.fr.esgi.kiosk.helpers.CredentialsHelper;
import main.fr.esgi.kiosk.helpers.HttpHelper;
import main.fr.esgi.kiosk.models.StoreCredentials;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Stores {

    public static void login(String email, String password) throws IOException, ParseException {

        CredentialsHelper credentialsHelper = new CredentialsHelper();
        Properties routes = credentialsHelper.getRoutes();

        System.out.println(routes.getProperty("login"));

        List<NameValuePair> credentials = new ArrayList<>();
        credentials.add(new BasicNameValuePair("email", email));
        credentials.add(new BasicNameValuePair("password", password));

        JSONObject jsonObject = HttpHelper.httpPostRequest(routes.getProperty("login"), credentials);

        StoreCredentials storeCredentials = new StoreCredentials(email, password);
        storeCredentials.setJwt((String) jsonObject.get("jwt"));

        credentialsHelper.createCredentials(storeCredentials);
    }


}
