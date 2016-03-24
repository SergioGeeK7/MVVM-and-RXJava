
package com.santiagoalvarez.grabilityapplicanttest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImContentType implements Serializable {

    private static final long serialVersionUID = -6731141952135139610L;
    @SerializedName("attributes")
    @Expose
    private ImContentTypeAttributes attributes;

    /**
     * @return The attributes
     */
    public ImContentTypeAttributes getAttributes() {
        return attributes;
    }

    /**
     * @param attributes The attributes
     */
    public void setAttributes(ImContentTypeAttributes attributes) {
        this.attributes = attributes;
    }

}
