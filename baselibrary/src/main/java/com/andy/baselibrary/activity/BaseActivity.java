package com.andy.baselibrary.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.andy.baselibrary.utils.LogUtil;

import butterknife.ButterKnife;


/**
 * Created by Andy on 2016/11/15.
 * <ul>
 *      可以使用注解“BindView”实现依赖注入，例如：
 *      <li>@BindView(R.id.user) EditText username;</>
 *      <li>@BindString(R.string.login_error) String loginErrorMessage;</>
 * </>
 * 更多使用细节，请参照：<a href="http://jakewharton.github.io/butterknife/">Butter Knife Field and method binding for Android views</a>
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        ButterKnife.bind(this);

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
