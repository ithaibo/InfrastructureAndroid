package com.andy.infrastructure.demos.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.andy.infrastructure.R;


/**
 * Created by Andy on 2017/1/5.
 */

public class DataBindActivity extends AppCompatActivity {
    private Customer user;
    private DataBindPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBind binding =  DataBindingUtil.setContentView(this, R.layout.act_data_bind_demo);


        user = new Customer();
        binding.setUser(user);

        user.setName("Andy");
        user.setMobile("13866668888");

        mPresenter = new DataBindPresenter(this);
        mPresenter.setmUser(user);

        binding.setUserMV(new CustomerMV());

        binding.setPresenter(mPresenter);


    }

}
