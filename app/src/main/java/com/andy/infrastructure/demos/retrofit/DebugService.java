package com.andy.infrastructure.demos.retrofit;

import com.google.gson.JsonObject;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Andy on 2017/9/19.
 */

public interface DebugService {
    //http://renwu.gidoor.com/api/runner/order/findCircleNear?lon=121.445291&lat=31.199112&page=1
    @GET("api/runner/order/findCircleNear?lon=121.445291&lat=31.199112&page=1")
    Observable<JsonObject> getOrderList();
}
