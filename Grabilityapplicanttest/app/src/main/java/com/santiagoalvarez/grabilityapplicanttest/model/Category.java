
package com.santiagoalvarez.grabilityapplicanttest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable {

    private static final long serialVersionUID = 2384380758840359656L;
    @SerializedName("attributes")
    @Expose
    private CategoryAttributes attributes;

    /**
     * @return The attributes
     */
    public CategoryAttributes getAttributes() {
        return attributes;
    }

    /**
     * @param attributes The attributes
     */
    public void setAttributes(CategoryAttributes attributes) {
        this.attributes = attributes;
    }

}
