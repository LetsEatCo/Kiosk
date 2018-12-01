package main.fr.esgi.kiosk.models;

public class CreditCard {


    private String number;
    private String expMonth;
    private String expYear;
    private String cvc;

    public CreditCard(String number, String expMonth, String expYear, String cvc) {
        this.number = number;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.cvc = cvc;
    }

    public String getNumber() {
        return number;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public String getCvc() {
        return cvc;
    }
}
