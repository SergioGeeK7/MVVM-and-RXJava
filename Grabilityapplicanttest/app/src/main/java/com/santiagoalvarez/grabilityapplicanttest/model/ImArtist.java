
package com.santiagoalvarez.grabilityapplicanttest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImArtist extends SimpleLabel implements Serializable {

    private static final long serialVersionUID = 8715534241563951657L;
    @SerializedName("attributes")
    @Expose
    private ImArtistAttributes attributes;

    /**
     * @return The attributes
     */
    public ImArtistAttributes getAttributes() {
        return attributes;
    }

    /**
     * @param attributes The attributes
     */
    public void setAttributes(ImArtistAttributes attributes) {
        this.attributes = attributes;
    }

}
