
package com.santiagoalvarez.grabilityapplicanttest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Link implements Serializable {

    private static final long serialVersionUID = 7123899704218408476L;
    @SerializedName("attributes")
    @Expose
    private LinkAttributes attributes;

    /**
     * @return The attributes
     */
    public LinkAttributes getAttributes() {
        return attributes;
    }

    /**
     * @param attributes The attributes
     */
    public void setAttributes(LinkAttributes attributes) {
        this.attributes = attributes;
    }

}
