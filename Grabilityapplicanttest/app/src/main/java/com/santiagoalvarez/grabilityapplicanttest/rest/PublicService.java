package com.santiagoalvarez.grabilityapplicanttest.rest;


import com.santiagoalvarez.grabilityapplicanttest.model.Feed;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by santiagoalvarezmonsalve on 3/19/16.
 */
public class PublicService {

    private ApiService apiService;

    public PublicService(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<Feed> feed() {
        return apiService.feed()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
