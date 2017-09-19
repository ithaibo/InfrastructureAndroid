package com.andy.baselibrary.net;

import android.content.Context;
import android.text.TextUtils;


import com.andy.baselibrary.utils.LogUtil;
import com.andy.baselibrary.utils.NetWorkUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Andy on 2016/12/19.
 */

public class GenServiceUtil {
//    private String BASE_URL;
    private Retrofit retrofit;
    private static GenServiceUtil instance;
    private Context context;
    private String ticket;
    private static final String TAG = "HTTP";

    public static GenServiceUtil genInstance(String BASE_URL, Context context, String ticket) {
        if (context == null || TextUtils.isEmpty(ticket) || TextUtils.isEmpty(BASE_URL)) {
            return null;
        }

        if (instance == null) {
            instance = new GenServiceUtil();
//            instance.BASE_URL = BASE_URL;
            instance.initRetrofit(BASE_URL);
        }

        instance.context = context;
        instance.ticket = ticket;

        return instance;
    }

    public static GenServiceUtil genInstance(String BASE_URL, Context context) {
        if (context == null || TextUtils.isEmpty(BASE_URL)) {
            return null;
        }

        if (instance == null) {
            instance = new GenServiceUtil();
//            instance.BASE_URL = BASE_URL;
            instance.initRetrofit(BASE_URL);
        }

        instance.context = context;

        return instance;
    }

    private void initRetrofit(String BASE_URL) {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getOkClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

    public OkHttpClient getOkClient() {
        OkHttpClient client1;
        client1 = getUnsafeOkHttpClient();
        return client1;
    }

    public OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Install the all-trusting trust manager
            // Create an ssl socket factory with our all-trusting manager
            return new OkHttpClient.Builder()
                    .readTimeout(20, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(getHttpLoggingInterceptor())
                    .addInterceptor(new HttpHeadInterceptor())
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtil.i(TAG, "retrofitBack = " + message);
            }
        });

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return loggingInterceptor;
    }

    class HttpHeadInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder();
            builder.addHeader("G-AGENT", getGAgent());
            builder.addHeader("Accept", "application/json;versions=1");
            if (!TextUtils.isEmpty(ticket)) {
                builder.addHeader("ticket", ticket);
            }
            if (NetWorkUtils.isNetworkConnected(context)) {
                int maxAge = 60;
                builder.addHeader("Cache-Control", "public, max-age=" + maxAge);
            } else {
                int maxStale = 60 * 60 * 24 * 28;
                builder.addHeader("Cache-Control", "public, only-if-cached, max-stale=" + maxStale);
            }
            Response response = null;
            try{
                response = chain.proceed(builder.build());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }
    }

    protected String getGAgent() {
        return "2";
    }
}
