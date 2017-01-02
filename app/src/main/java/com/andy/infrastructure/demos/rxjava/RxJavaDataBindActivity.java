package com.andy.infrastructure.demos.rxjava;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.infrastructure.R;

import butterknife.OnClick;

/**
 * 有三种数据改变通知机制：
 * Observable 对象
 * ObservableFields
 * 和 observable 集合
 */
public class RxJavaDataBindActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_java_data_bind;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initViews() {

    }

    @OnClick({
            R.id.btn_4_observable_instance,
            R.id.btn_4_observable_fields,
            R.id.btn_4_observable_collection})
    void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_4_observable_instance:
                break;
            case R.id.btn_4_observable_fields:
                break;
            case R.id.btn_4_observable_collection:
                break;
        }
    }
}
