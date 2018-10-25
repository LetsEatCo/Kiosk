package main.fr.esgi.kiosk.helpers;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.net.URL;
import java.util.List;

public class HttpHelper {


    public static Object httpGetRequest(String url, String jwt) throws IOException, ParseException {

        Object body;

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Authorization", "Bearer " + jwt);

        try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {

            body = JsonHelper.getResponseBody(httpResponse);

        }

        return body;
    }

    public static Object httpPostRequest(String url, List<NameValuePair> credentials) throws IOException, ParseException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(credentials));
        CloseableHttpResponse response = httpClient.execute(httpPost);

        return JsonHelper.getResponseBody(response);

    }

    public static Object getMe(String url, String jwt) throws IOException, ParseException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Authorization", "Bearer " + jwt);
        CloseableHttpResponse response = httpClient.execute(httpGet);

        return JsonHelper.getResponseBody(response);

    }

    private static void downloadImage(String url, String destination) throws IOException {

        URL imageUrl = new URL(url);

        InputStream inputStream = new BufferedInputStream(imageUrl.openStream());
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(destination));

        int readByte;
        while ((readByte = inputStream.read())!=-1){

            outputStream.write(readByte);
        }
        inputStream.close();
        outputStream.close();
    }


}
