package com.andy.baselibrary.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.andy.baselibrary.utils.LogUtil;

/**
 * Created by andy on 17-4-4.
 */

public abstract class DataBindActivity<DB extends ViewDataBinding> extends FragmentActivity {
    protected Context mContext;
    protected Handler handler;
    protected DB mDataBind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBind = DataBindingUtil.setContentView(this, getLayoutId());

        mContext = this;

        initData();
        initViews();

        this.handler = new Handler(getMainLooper());
        LogUtil.d("--onCreate1");
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        LogUtil.d("--onCreate2");
    }

    protected abstract int getLayoutId();

    /**
     * get bundle from intent.
     */
    protected abstract void initData();

    /**
     * initialize views.
     */
    protected abstract void initViews();


    protected void showToast(String content) {
        showToast(content, Toast.LENGTH_SHORT);
    }

    protected void showToast(String content, int duration) {
        Toast.makeText(mContext, content, duration).show();
    }

    /**
     * To show the dialog to login when the session timeout.
     */
    protected void showLoginDialog() {
    }

    protected void toSomeActivity(Class activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    protected void toSomeActivityForResult(int requestCode, Class activityClass, Bundle data, String dataName) {
        Intent intent = new Intent(this, activityClass);
        if (data != null) {
            intent.putExtra(dataName, data);
        }
        startActivityForResult(intent, requestCode);
    }
}
