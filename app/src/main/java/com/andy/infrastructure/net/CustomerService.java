package com.andy.infrastructure.net;

import com.andy.infrastructure.bean.Customer;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Andy on 2016/12/19.
 */

public interface CustomerService {
    @GET("api/customer/info")
    Call<Customer> getCustomerInfo();

    @GET("api/customer/{id}/info?sort=desc&id=100010")
    Call<Customer> getCustomerInfo(@Path("id") int id);

    @GET("api/customer/info")
    Call<Customer> getCustomersSort(@Query("sort") String sort);

    @GET("api/customer/info")
    Call<Customer> getCustomersByParamsMap(@QueryMap Map<String, String> params);

    @POST("api/customer/add")
    Call<Customer> addCustomer(@Body Customer body);
}
