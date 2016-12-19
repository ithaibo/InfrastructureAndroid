package com.andy.baselibrary.utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Andy on 2016/12/19.
 */

public class GenServiceUtil {
    private static final String BASE_URL = "https://192.168.1.24:3000/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.client(httpClient.build()).build();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
