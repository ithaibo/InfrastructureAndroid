package com.andy.infrastructure.demos.retrofit;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.baselibrary.net.GenServiceUtil;
import com.andy.baselibrary.utils.LogUtil;
import com.andy.infrastructure.R;
import com.andy.infrastructure.bean.Customer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by Andy on 2016/12/22.
 */

public class SimpleRetrofit extends BaseActivity {
    @BindView(R.id.btn_get_net_act_simple_retrofit)
    Button btnGetNet;
    private Call<Customer> call;

    @Override
    protected int getLayoutId() {
        return R.layout.retrofit_act_simple;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }

    @OnClick({R.id.btn_get_net_act_simple_retrofit})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_net_act_simple_retrofit:
                justGetCustomerInfo();
                break;
        }
    }

    public void justGetCustomerInfo() {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("id", "124");
        requestMap.put("sorrt", "desc");

        //getCustomerById(1);getCustomersSort("desc"); getCustomersByParamsMap
        call = new GenServiceUtil("http://192.168.1.24:3000/")
                .createService(com.andy.infrastructure.net.CustomerService.class)
                        .getCustomersByParamsMap(requestMap);
        AsyncTask<String, Integer, Customer> task = new AsyncTask<String, Integer, Customer>() {
            @Override
            protected Customer doInBackground(String... strings) {
                try {
                    Response<Customer> result = call.execute();
                    LogUtil.d("请求地址：" + call.request().url() + "; 请求参数:" + call.request().headers().toString());
                    LogUtil.d("返回数据--" + result.headers());
                    Response.success(result);
                    return result.body();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Customer customer) {
                super.onPostExecute(customer);
//                showToast(customer.toString(), Toast.LENGTH_LONG);
            }
        };

        task.execute(null, null);
    }

    @Override
    protected void onPause() {
        super.onPause();
        call.cancel();
        LogUtil.d("请求已取消...");
    }
}
