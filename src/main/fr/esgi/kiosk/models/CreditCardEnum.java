package main.fr.esgi.kiosk.models;

public enum CreditCardEnum {

    NUMBER("number"),
    EXP_MONTH("exp_month"),
    EXP_YEAR("exp_year"),
    CVC( "cvc");

    public String value;

    CreditCardEnum(String value) {
        this.value = value;
    }

}
