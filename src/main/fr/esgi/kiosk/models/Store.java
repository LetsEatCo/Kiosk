package main.fr.esgi.kiosk.models;

import java.util.ArrayList;

public class Store extends Ressource {

    private String name;
    private String email;
    private String phoneNumber;
    private String imageUrl;
    private Sections sections;


    public Store(String uuid, String name, String email, String phoneNumber, String imageUrl, Sections sections) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.imageUrl = imageUrl;
        this.sections = sections;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Sections getSections() {
        return sections;
    }

    public void setSections(Sections sections) {
        this.sections = sections;
    }
}
