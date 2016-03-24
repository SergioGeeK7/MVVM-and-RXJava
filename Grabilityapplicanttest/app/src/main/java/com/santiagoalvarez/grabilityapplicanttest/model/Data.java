package com.santiagoalvarez.grabilityapplicanttest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by santiagoalvarezmonsalve on 3/19/16.
 */
public class Data implements Serializable {
    private static final long serialVersionUID = 2571509113632389114L;

    @SerializedName("feed")
    @Expose
    private Feed feed;

    public Feed getFeed() {
        return feed;
    }
}
