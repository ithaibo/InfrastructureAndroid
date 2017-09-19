package com.andy.baselibrary.net;

import android.util.ArrayMap;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by Andy on 2017/5/15.
 */

public class OkHttpUitls {

    public static OkHttpClient gennerateOkHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    public static OkHttpClient gennerateOkHttpClient(final ArrayMap<String, String> headers) {
        OkHttpClient client = new OkHttpClient();

        if (headers!=null) {
            client.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    for (String key : headers.keySet()) {
                        String valHear = headers.get(key);
                        chain.request()
                                .headers()
                                .newBuilder()
                                .add(key, valHear);
                    }
                    return null;
                }
            });
        }

        return client;
    }
}
