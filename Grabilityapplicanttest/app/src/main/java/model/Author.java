
package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Author implements Serializable {

    private static final long serialVersionUID = 6154815181255407503L;

    @SerializedName("name")
    @Expose
    private SimpleLabel name;
    @SerializedName("uri")
    @Expose
    private SimpleLabel uri;

    /**
     * @return The name
     */
    public SimpleLabel getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(SimpleLabel name) {
        this.name = name;
    }

    /**
     * @return The uri
     */
    public SimpleLabel getUri() {
        return uri;
    }

    /**
     * @param uri The uri
     */
    public void setUri(SimpleLabel uri) {
        this.uri = uri;
    }

}
