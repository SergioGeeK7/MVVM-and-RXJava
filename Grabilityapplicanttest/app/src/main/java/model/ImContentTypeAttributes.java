
package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImContentTypeAttributes extends SimpleLabel implements Serializable {

    private static final long serialVersionUID = 2984338107261966407L;
    @SerializedName("term")
    @Expose
    private String term;

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
}
