package com.andy.infrastructure.demos.retrofit;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.baselibrary.net.GenServiceUtil;
import com.andy.baselibrary.utils.LogUtil;
import com.andy.infrastructure.R;
import com.andy.infrastructure.bean.Customer;
import com.andy.infrastructure.net.CustomerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by Andy on 2016/12/22.
 */

public class SimpleRetrofit extends BaseActivity {
    @BindView(R.id.btn_get_net_act_simple_retrofit)
    Button btnGetNet;
    @BindView(R.id.btn_get_net_act_simple_retrofit)
    Button btnGetNetActSimpleRetrofit;
    @BindView(R.id.btn_add_one_act_simple_retrofit)
    Button btnAddOneActSimpleRetrofit;
    private List<Call> callList = new ArrayList<>();

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

    @OnClick({R.id.btn_get_net_act_simple_retrofit,
            R.id.btn_add_one_act_simple_retrofit})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_net_act_simple_retrofit:
                justGetCustomerInfo();
                break;
            case R.id.btn_add_one_act_simple_retrofit:
                addCustomer();
                break;
        }
    }

    public void justGetCustomerInfo() {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("id", "124");
        requestMap.put("sorrt", "desc");

        //getCustomerById(1);getCustomersSort("desc"); getCustomersByParamsMap
        final Call<Customer> getCustomerCall = GenServiceUtil.genInstance("http://192.168.1.24:3000/")
                .createService(CustomerService.class)
                .getCustomersByParamsMap(requestMap);
        callList.add(getCustomerCall);

        AsyncTask<String, Integer, Customer> task = new AsyncTask<String, Integer, Customer>() {
            @Override
            protected Customer doInBackground(String... strings) {
                try {
                    Response<Customer> result = getCustomerCall.execute();
                    LogUtil.d("请求地址：" + getCustomerCall.request().url() + "; 请求参数:" + getCustomerCall.request().headers().toString());
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

    public void addCustomer() {
        Customer newItem = new Customer();
        newItem.setName("Andy");
        newItem.setEmail("andy@host.com");
        newItem.setSex(1);

        Call<Customer> addCustomerCall = GenServiceUtil.genInstance("http://192.168.1.24:3000/")
                .createService(CustomerService.class)
                .addCustomer(newItem);

        callList.add(addCustomerCall);

        AsyncTask<Call, Integer, Customer> addTask = new AsyncTask<Call, Integer, Customer>() {
            @Override
            protected Customer doInBackground(Call... calls) {
                try {
                    LogUtil.d("请求路径:" + calls[0].request().url() + "参数：" + calls[0].request().method());
                    Response<Customer> reponse = calls[0].execute();
                    LogUtil.d("返回数据：" + reponse.headers().toString() + "code:" + reponse.code());
                    return reponse.body();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };

        addTask.execute(addCustomerCall);
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (Call item : callList) {
            try {
                LogUtil.d("请求已取消...");
                item.cancel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
