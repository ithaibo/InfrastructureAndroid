package com.andy.baselibrary.net;

import retrofit2.Retrofit;

/**
 * Created by Andy on 2016/12/23.
 */

public class HttpUtil {
    private static HttpUtil instance;
    private Retrofit retrofit;

    private HttpUtil(){}

    public static synchronized HttpUtil getInstance() {
        if (instance == null) {
            instance = new HttpUtil();
        }
        return instance;
    }

    private void initCommonParam(){

    }
}
