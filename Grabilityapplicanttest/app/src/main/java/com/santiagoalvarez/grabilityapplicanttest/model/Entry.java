package com.santiagoalvarez.grabilityapplicanttest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by santiagoalvarezmonsalve on 3/19/16.
 */
public class Entry implements Serializable {
    private static final long serialVersionUID = 7558932416779655305L;

    @SerializedName("im:name")
    @Expose
    private SimpleLabel imName;

    @SerializedName("im:image")
    @Expose
    private List<ImImage> imImage;

    @SerializedName("summary")
    @Expose
    private SimpleLabel summary;

    @SerializedName("im:price")
    @Expose
    private ImPrice imPrice;

    @SerializedName("im:contentType")
    @Expose
    private ImContentType imContentType;

    @SerializedName("rights")
    @Expose
    private SimpleLabel rights;

    @SerializedName("title")
    @Expose
    private SimpleLabel title;

    @SerializedName("link")
    @Expose
    private Link link;

    @SerializedName("id")
    @Expose
    private Id id;

    @SerializedName("im:artist")
    @Expose
    private ImArtist imArtist;

    @SerializedName("category")
    @Expose
    private Category category;

    @SerializedName("im:releaseDate")
    @Expose
    private ImReleaseDate imReleaseDate;

    public SimpleLabel getImName() {
        return imName;
    }

    public List<ImImage> getImImage() {
        return imImage;
    }

    public SimpleLabel getSummary() {
        return summary;
    }

    public ImPrice getImPrice() {
        return imPrice;
    }

    public ImContentType getImContentType() {
        return imContentType;
    }

    public SimpleLabel getRights() {
        return rights;
    }

    public SimpleLabel getTitle() {
        return title;
    }

    public Link getLink() {
        return link;
    }

    public Id getId() {
        return id;
    }

    public ImArtist getImArtist() {
        return imArtist;
    }

    public Category getCategory() {
        return category;
    }

    public ImReleaseDate getImReleaseDate() {
        return imReleaseDate;
    }
}
