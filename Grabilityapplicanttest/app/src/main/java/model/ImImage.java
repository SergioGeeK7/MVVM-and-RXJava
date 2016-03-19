
package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImImage extends SimpleLabel implements Serializable {

    private static final long serialVersionUID = 1139488738087777834L;
    @SerializedName("attributes")
    @Expose
    private ImImageAttributes attributes;

    /**
     * @return The attributes
     */
    public ImImageAttributes getAttributes() {
        return attributes;
    }

    /**
     * @param attributes The attributes
     */
    public void setAttributes(ImImageAttributes attributes) {
        this.attributes = attributes;
    }

}
