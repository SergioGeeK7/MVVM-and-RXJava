
package com.santiagoalvarez.grabilityapplicanttest.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImImageAttributes implements Serializable {

    private static final long serialVersionUID = 3166895956920607962L;
    @SerializedName("height")
    @Expose
    private int height;

    /**
     * @return The height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height The height
     */
    public void setHeight(int height) {
        this.height = height;
    }

}
