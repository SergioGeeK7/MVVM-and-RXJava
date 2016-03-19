
package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImPrice extends SimpleLabel implements Serializable {

    private static final long serialVersionUID = -6804996794082517840L;
    @SerializedName("attributes")
    @Expose
    private ImPriceAttributes attributes;

    /**
     * @return The attributes
     */
    public ImPriceAttributes getAttributes() {
        return attributes;
    }

    /**
     * @param attributes The attributes
     */
    public void setAttributes(ImPriceAttributes attributes) {
        this.attributes = attributes;
    }

}
