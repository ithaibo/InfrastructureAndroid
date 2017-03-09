package com.andy.infrastructure.demos.material;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.infrastructure.R;
import com.andy.infrastructure.bean.DemoFragmentBean;
import com.andy.infrastructure.presenter.ItemMenuMaterialPresenter;

/**
 * Created by Smily on 2017/1/2.
 */

public class Material1Activity extends BaseActivity implements View.OnClickListener, ItemMenuMaterialPresenter {
    private Fragment simpleWidgetFrg;
    private Fragment textInputLayouttFrg;
    private static final String TAG_DIALOG = "materialMenuDialog";
    private FragmentTransaction ft;
    private ActBind actBind;
    private MaterialMenuDialog menuDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.act_material_1;
    }

    @Override
    protected void initData() {

        actBind = (ActBind) mDataBind;
        simpleWidgetFrg = new SimpleDesighWidgetFragment();
        textInputLayouttFrg = new TextInputlayoutFragment();

        actBind.fabShowMenu.setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        replaceFragemntAction(new DemoFragmentBean()
                .setClassName(TextInputlayoutFragment.class));
    }

    @Override
    public void onClick(View v) {
        menuDialog = new MaterialMenuDialog();
        menuDialog.show(getSupportFragmentManager(), TAG_DIALOG);
    }

    @Override
    public void replaceFragemntAction(DemoFragmentBean itemData) {
        FragmentManager fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        try {
            ft.replace(actBind.flFrgContent.getId(), itemData.getClassName().newInstance());
//            ft.addToBackStack(null);
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
