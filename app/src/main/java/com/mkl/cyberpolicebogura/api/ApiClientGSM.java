package com.mkl.cyberpolicebogura.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.mkl.cyberpolicebogura.utils.Data.BaseUrl;

/**
 * Created by TAOHID on 1/21/2018.
 */

public class ApiClientGSM {

    private static final String BASE_URL = "https://cellphonetrackers.org/gsm/classes/";
    private static Retrofit retrofit = null;

    private static Retrofit getClient() {
        if (retrofit==null) {
            if (retrofit == null) {
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(3, TimeUnit.MINUTES)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(15, TimeUnit.SECONDS)
                        .build();
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
        }
        return retrofit;
    }

    public static ApiInterface getApiInterface(){
        return getClient().create(ApiInterface.class);
    }
}
