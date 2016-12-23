package com.andy.baselibrary.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Andy on 2016/12/19.
 */

public class GenServiceUtil {
    private  String BASE_URL;

    private Retrofit retrofit;

    public GenServiceUtil(String BASE_URL) {
        this.BASE_URL = BASE_URL;
        initRetrofit(BASE_URL);
    }

    private void initRetrofit(String BASE_URL) {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
    }

    public <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
