package main.utilities;

import java.io.Serializable;
import java.util.HashMap;

/**
 * A class to represent various currencies
 */
public class Currency implements Serializable{
    /**
     * The type of currency
     */
    private String type;

    /**
     * The conversion rate of the currency to CAD
     */
    private float conversionValue;

    /**
     * A HashMap matching currencies to their conversion values in CAD
     */
    private HashMap<String, Float> currencies = new HashMap<>();

    /**
     * The currency object
     *
     * @param typeRequested the currency needed
     */
    public Currency(String typeRequested){
        currencies.put("CAD", (float) 1.0);
        currencies.put("USD", (float) 1.34);
        currencies.put("EUR", (float) 1.51);
        switch(typeRequested){
            case "CAD":
                type = "CAD";
                conversionValue = currencies.get(type);
                break;
            case "USD":
                type = "USD";
                conversionValue = currencies.get(type);
                break;
            case "EUR":
                type = "EUR";
                conversionValue = currencies.get(type);
                break;
        }
    }


    /**
     * Getter for the conversion rate
     * @return the conversion rate of this currency
     */
    public float getConversionValue() {
        return conversionValue;
    }

    /**
     * Getter for type
     * @return the currency's type
     */
    public String getType(){
        return this.type;
    }
}
