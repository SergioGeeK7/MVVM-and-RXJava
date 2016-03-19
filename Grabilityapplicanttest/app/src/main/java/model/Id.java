
package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Id extends SimpleLabel implements Serializable {

    private static final long serialVersionUID = 2790410977969556089L;
    @SerializedName("attributes")
    @Expose
    private IdAttributes attributes;

    /**
     * @return The attributes
     */
    public IdAttributes getAttributes() {
        return attributes;
    }

    /**
     * @param attributes The attributes
     */
    public void setAttributes(IdAttributes attributes) {
        this.attributes = attributes;
    }

}
