
package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImPriceAttributes implements Serializable {

    private static final long serialVersionUID = 1210994075396577194L;
    @SerializedName("amount")
    @Expose
    private double amount;
    @SerializedName("currency")
    @Expose
    private String currency;

    /**
     * @return The amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount The amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
