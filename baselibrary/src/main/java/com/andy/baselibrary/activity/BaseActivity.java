package com.andy.baselibrary.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

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

public abstract class BaseActivity extends FragmentActivity {
    protected Context mContext;
    protected Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(getLayoutId());

        ButterKnife.bind(this);

        mContext = this;

        initData();
        initViews();

        this.handler = new Handler(getMainLooper());
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
