package com.andy.infrastructure.demos.retrofit;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.baselibrary.net.GenServiceUtil;
import com.andy.baselibrary.utils.LogUtil;
import com.andy.infrastructure.R;
import com.andy.infrastructure.bean.Customer;

import java.io.IOException;

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

    private void justGetCustomerInfo() {
        final Call<Customer> call = new GenServiceUtil("http://192.168.1.24:3000/")
                .createService(com.andy.infrastructure.net.CustomerService.class)
                        .getCustomerInfo();
        AsyncTask<String, Integer, Customer> task = new AsyncTask<String, Integer, Customer>() {
            @Override
            protected Customer doInBackground(String... strings) {
                try {
                    Response<Customer> result = call.execute();
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
                LogUtil.d("请求结果：" + customer );
            }
        };

        task.execute(null, null);
    }

}
