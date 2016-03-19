package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CategoryAttributes extends SimpleLabel implements Serializable {

    private static final long serialVersionUID = -7174282538370768812L;
    @SerializedName("im:id")
    @Expose
    private int imId;
    @SerializedName("term")
    @Expose
    private String term;
    @SerializedName("scheme")
    @Expose
    private String scheme;

    /**
     * @return The imId
     */
    public int getImId() {
        return imId;
    }

    /**
     * @param imId The im:id
     */
    public void setImId(int imId) {
        this.imId = imId;
    }

    /**
     * @return The term
     */
    public String getTerm() {
        return term;
    }

    /**
     * @param term The term
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * @return The scheme
     */
    public String getScheme() {
        return scheme;
    }

    /**
     * @param scheme The scheme
     */
    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

}
