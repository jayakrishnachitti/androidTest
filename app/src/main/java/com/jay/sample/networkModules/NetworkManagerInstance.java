package com.jay.sample.networkModules;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/***
 * Retrofit Instance for handling network operations
 */
public class NetworkManagerInstance {

    public static final String BASE_URL = "http://mobcategories.s3-website-eu-west-1.amazonaws.com";
    private static Retrofit retrofit;

    public static Retrofit getNetworkManagerInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
