package com.santiagoalvarez.grabilityapplicanttest.rest;

import com.santiagoalvarez.grabilityapplicanttest.model.Feed;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by santiagoalvarezmonsalve on 3/19/16.
 */
public interface ApiService {

    @GET("us/rss/topfreeapplications/limit=20/json")
    Observable<Feed> feed();
}
