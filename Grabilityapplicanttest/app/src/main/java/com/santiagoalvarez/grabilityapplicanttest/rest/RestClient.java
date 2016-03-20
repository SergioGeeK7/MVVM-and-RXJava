package com.santiagoalvarez.grabilityapplicanttest.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
                .client(buildClient())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        publicService = new PublicService(apiService);
    }

    private OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();
    }

    public PublicService getPublicService() {
        return publicService;
    }

    public static RestClient getInstance() {
        return instance;
    }
}
