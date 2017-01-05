package com.andy.infrastructure.demos.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.andy.infrastructure.R;
import com.andy.infrastructure.bean.Customer;
import com.andy.infrastructure.databinding.ActDataBindDemoBinding;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Andy on 2017/1/5.
 */

public class DataBindActivity extends AppCompatActivity {

    @BindView(R.id.btnChangeMobile)
    Button btnChangeMobile;
    private Customer user;
    private ActDataBindDemoBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.act_data_bind_demo);

        ButterKnife.bind(this);

        user = new Customer();
        user.setName("Andy");
        user.setMobile("13866668888");
        binding.setUser(user);

        ActDataBindDemoBinding.inflate(getLayoutInflater());
    }

    @OnClick(R.id.btnChangeMobile)
    void onClick(View view) {
        user.setMobile("18966669999");
        binding.setUser(user);
    }
}
