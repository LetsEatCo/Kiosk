package main.fr.esgi.kiosk.helpers;

import org.apache.http.HttpResponse;
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
import java.net.MalformedURLException;
import java.net.URL;

public class JsonHelper {


    public JsonObject getDataFromUrl(String url) throws IOException {

        URL urlObject = new URL(url);

        try(InputStream is = urlObject.openStream();
            JsonReader rdr = Json.createReader(is)){

            JsonObject obj = rdr.readObject();

            return obj;
        }

    }

    public static JSONObject parseJsonData(String json) throws ParseException {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject)jsonParser.parse(json);

        return (JSONObject) jsonObject.get("data");
    }

    public static JSONObject getResponseBody(HttpResponse httpResponse) throws IOException, ParseException {


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


}
