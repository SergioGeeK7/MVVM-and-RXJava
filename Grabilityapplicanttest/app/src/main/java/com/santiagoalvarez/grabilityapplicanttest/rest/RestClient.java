package com.santiagoalvarez.grabilityapplicanttest.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by santiagoalvarezmonsalve on 3/19/16.
 */
public class RestClient {

    private static RestClient instance = new RestClient();

    private PublicService publicService;

    public RestClient() {
        Gson gson = new GsonBuilder()
                .setDateFormat(ApiConstants.API_DATE_FORMAT)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        publicService = new PublicService(apiService);
    }

    public PublicService getPublicService() {
        return publicService;
    }

    public static RestClient getInstance() {
        return instance;
    }
}
