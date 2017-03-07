package com.andy.baselibrary.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Andy on 2016/12/19.
 */

public class GenServiceUtil {
    private  String BASE_URL;
    private Retrofit retrofit;
    private static GenServiceUtil instance;

    public static GenServiceUtil genInstance(String BASE_URL) {
        if (instance == null) {
            instance = new GenServiceUtil();
            instance.BASE_URL = BASE_URL;
            instance.initRetrofit(BASE_URL);
        }
        return instance;
    }

    private void initRetrofit(String BASE_URL) {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
    }

    public <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
