package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by santiagoalvarezmonsalve on 3/19/16.
 */
public class Feed implements Serializable {
    private static final long serialVersionUID = 1697062810098057770L;

    @SerializedName("author")
    @Expose
    private Author author;

    @SerializedName("entry")
    @Expose
    private List<Entry> entries;

    @SerializedName("updated")
    @Expose
    private SimpleLabel updated;

    @SerializedName("rights")
    @Expose
    private SimpleLabel rights;

    @SerializedName("title")
    @Expose
    private SimpleLabel title;

    @SerializedName("icon")
    @Expose
    private SimpleLabel icon;

    @SerializedName("link")
    @Expose
    private List<Link> links;

    @SerializedName("id")
    @Expose
    private SimpleLabel id;

    public Author getAuthor() {
        return author;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public SimpleLabel getUpdated() {
        return updated;
    }

    public SimpleLabel getRights() {
        return rights;
    }

    public SimpleLabel getTitle() {
        return title;
    }

    public SimpleLabel getIcon() {
        return icon;
    }

    public List<Link> getLinks() {
        return links;
    }

    public SimpleLabel getId() {
        return id;
    }
}
