package com.santiagoalvarez.grabilityapplicanttest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by santiagoalvarezmonsalve on 3/19/16.
 */
public class SimpleLabel implements Serializable {
    private static final long serialVersionUID = -7871863927318072579L;

    @SerializedName("label")
    @Expose
    private String label;

    /**
     * @return The label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label The label
     */
    public void setLabel(String label) {
        this.label = label;
    }
}
