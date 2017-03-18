package com.andy.infrastructure.demos.web;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.baselibrary.utils.LogUtil;
import com.andy.infrastructure.R;
import com.andy.infrastructure.bean.DemoFragmentBean;
import com.andy.infrastructure.bean.ListDialogItemDataBean;
import com.andy.infrastructure.demos.material.ActBind;
import com.andy.infrastructure.demos.material.MaterialMenuDialog;
import com.andy.infrastructure.demos.material.TextInputlayoutFragment;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2017/3/15.
 */

public class WebViewActivity extends BaseActivity implements ListDialogItemPresenter<String> {
    private MaterialMenuDialog menuDialog;
    private FragmentTransaction ft;
    private static final String TAG_DIALOG = "webMenuDialog";
    private WebActivityBinding mWebBind;

    private ListMenuDialog materialMenuDialog;
    private List<ListDialogItemDataBean<String>> menuListData;

    @Override
    protected int getLayoutId() {
        return R.layout.act_web_view;
    }

    @Override
    protected void initData() {
        mWebBind = (WebActivityBinding) mDataBind;

        int screenDensity = getResources().getDisplayMetrics().densityDpi ;
        LogUtil.i("屏幕分辨率--" + screenDensity);

        menuListData = new ArrayList<>();

        Class<? extends Fragment> frgCls = SimpleWebViewFragment.class;

        menuListData.add(new ListDialogItemDataBean<String>()
        .setItemDesc("Simple Web Fragment")
        .setItemData(SimpleWebViewFragment.class.getName())
        .setPresenter(this));
    }

    @Override
    public void present(String data) {
        try {
            //反射加载Fragment
            Class<? extends Fragment> cl = (Class<? extends Fragment>) Class.forName(data);
            replaceFragemntAction(cl);
        } catch (ClassNotFoundException cle) {
            cle.printStackTrace();
        }
    }

    @Override
    protected void initViews() {
        replaceFragemntAction(TextInputlayoutFragment.class);

        mWebBind.fabShowMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (materialMenuDialog == null) {
                    materialMenuDialog = new ListMenuDialog();
                    materialMenuDialog.setDataSet(menuListData);
                }

                materialMenuDialog.show(getSupportFragmentManager(), "ListDialog");
            }
        });

    }

    public void replaceFragemntAction(Class<? extends Fragment> fragmentClass) {
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        try {

            Fragment fragment = fragmentClass.newInstance();

            ft.replace(mWebBind.flFrgContent.getId(), fragment);
            ft.commit();
            if (materialMenuDialog!=null /*&& materialMenuDialog.getDialog().isShowing()*/){
                materialMenuDialog.dismissAllowingStateLoss();
            }
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
