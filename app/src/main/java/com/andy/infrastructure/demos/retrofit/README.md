# Retrofit
这里主要为Retrofit的一些Demo

## SimpleRetrofit
这个Demo主要针对简单的网络请求，具体细节请看源码。
## 使用小结
* Retrofit.Builder.baseUrl(String url)这里的url需要以“/”格式结尾，例如："http:localhost:8080/";
* retrofit2.Retrofit.create(@NotNull java.lang.Class<T> service)这里的servervice中url不能以“/”开始，正确方式为："api/something";
* Retrofit默认是在Main Thread运行，因此异步需要自己处理。
### 项目示例代码
#####使用@Query添加**query string parameters**
```@GET("api/customer/info")	Call<Customer> getCustomerById(@Query("id") int id);```

####使用**{}**设置相关参数
```@GET("api/customer/{id}/info")
	Call<Customer> getCustomersSort();```

####使用@QueryMap添加复杂参数
```@GET("api/customer/info")
Call<Customer> getCustomersByParamsMap(@QueryMap Map<String, String> params);```

####使用@Body添加body
````@POST("api/customer/add")
Call<Customer> addCustomer(@Body Customer body);```


## 基本使用流程
1. 创建一个Retrofit.Builder
2. 为Retrofit.Builder添加client，可选择OkHttp
3. 执行call.excute()
4. 处理返回的结果
