
package com.santiagoalvarez.grabilityapplicanttest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImReleaseDate extends SimpleLabel implements Serializable {

    private static final long serialVersionUID = 219784963633336606L;
    @SerializedName("attributes")
    @Expose
    private ImReleaseDateAttributes attributes;

    /**
     * @return The attributes
     */
    public ImReleaseDateAttributes getAttributes() {
        return attributes;
    }

    /**
     * @param attributes The attributes
     */
    public void setAttributes(ImReleaseDateAttributes attributes) {
        this.attributes = attributes;
    }

}
