package com.santiagoalvarez.grabilityapplicanttest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IdAttributes implements Serializable {

    private static final long serialVersionUID = -622641766642672254L;
    @SerializedName("im:id")
    @Expose
    private String imId;
    @SerializedName("im:bundleId")
    @Expose
    private String imBundleId;

    /**
     * @return The imId
     */
    public String getImId() {
        return imId;
    }

    /**
     * @param imId The im:id
     */
    public void setImId(String imId) {
        this.imId = imId;
    }

    /**
     * @return The imBundleId
     */
    public String getImBundleId() {
        return imBundleId;
    }

    /**
     * @param imBundleId The im:bundleId
     */
    public void setImBundleId(String imBundleId) {
        this.imBundleId = imBundleId;
    }

}
