package main.fr.esgi.kiosk.models;

public class StoreCredentials {

    private String email;
    private String password;
    private String jwt;

    public StoreCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getJwt() {
        return jwt;
    }
}
