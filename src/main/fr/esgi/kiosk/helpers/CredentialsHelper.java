package main.fr.esgi.kiosk.config;

import main.fr.esgi.kiosk.models.StoreCredentials;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class CredentialsHandler {

    private static String path ="main/fr/esgi/kiosk/config/config.properties";
    private static String propertyFile ="config.properties";

    public void createCredentials(StoreCredentials storeCredentials) throws IOException {

        Properties properties = new Properties();
        OutputStream  outputStream = new FileOutputStream(path);

        // Conf properties
        properties.setProperty("email", storeCredentials.getEmail());
        properties.setProperty("password", storeCredentials.getPassword());
        properties.setProperty("jwt", storeCredentials.getJwt());
        properties.setProperty("kioskSerialNumber", storeCredentials.getKioskSerialNumber());

        // Store properties

        properties.store(outputStream, null);

        outputStream.close();

    }

}
