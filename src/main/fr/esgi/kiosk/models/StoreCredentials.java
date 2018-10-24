package main.fr.esgi.kiosk.models;

public class StoreCredentials {

    private String jwt;

    public StoreCredentials(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
