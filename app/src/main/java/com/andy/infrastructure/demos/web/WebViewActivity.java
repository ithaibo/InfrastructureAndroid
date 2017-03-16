package com.andy.infrastructure.demos.web;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.baselibrary.utils.LogUtil;
import com.andy.infrastructure.R;
import com.andy.infrastructure.bean.DemoFragmentBean;
import com.andy.infrastructure.demos.material.ActBind;
import com.andy.infrastructure.demos.material.MaterialMenuDialog;
import com.andy.infrastructure.demos.material.TextInputlayoutFragment;

/**
 * Created by Andy on 2017/3/15.
 */

public class WebViewActivity extends BaseActivity {
    private MaterialMenuDialog menuDialog;
    private ActBind actBind;
    private FragmentTransaction ft;
    private static final String TAG_DIALOG = "webMenuDialog";

    @Override
    protected int getLayoutId() {
        return R.layout.act_web_view;
    }

    @Override
    protected void initData() {
        WebActivityBinding mWebBind = (WebActivityBinding) mDataBind;

        int screenDensity = getResources().getDisplayMetrics().densityDpi ;
        LogUtil.i("屏幕分辨率--" + screenDensity);
    }

    @Override
    protected void initViews() {
        replaceFragemntAction(new DemoFragmentBean()
                .setClassName(TextInputlayoutFragment.class));
    }

    public void replaceFragemntAction(DemoFragmentBean itemData) {
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        try {
            ft.replace(actBind.flFrgContent.getId(), itemData.getClassName().newInstance());
            ft.commit();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
        if (menuDialog != null) {
            menuDialog.dismissAllowingStateLoss();
        }
    }
}
