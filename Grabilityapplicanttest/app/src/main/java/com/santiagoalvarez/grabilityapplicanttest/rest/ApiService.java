package com.santiagoalvarez.grabilityapplicanttest.rest;

import com.santiagoalvarez.grabilityapplicanttest.model.Data;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by santiagoalvarezmonsalve on 3/19/16.
 */
public interface ApiService {

    @GET("us/rss/topfreeapplications/limit=20/json")
    Observable<Data> feed();
}
