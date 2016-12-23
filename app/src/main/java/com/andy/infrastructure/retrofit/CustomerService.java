package com.andy.infrastructure.retrofit;

import com.andy.infrastructure.bean.Customer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Andy on 2016/12/23.
 */

public interface CustomerService {
    @GET("api/customer/info")
    Call<Customer> customerInfo();
}
