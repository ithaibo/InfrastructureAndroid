package com.andy.infrastructure.net;

import com.andy.infrastructure.bean.Customer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Andy on 2016/12/19.
 */

public interface CustomerService {
    @GET("api/customer/info")
    Call<Customer> getCustomerInfo();
}
