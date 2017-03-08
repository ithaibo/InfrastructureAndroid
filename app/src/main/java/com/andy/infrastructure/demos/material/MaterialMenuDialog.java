package com.andy.infrastructure.demos.material;

import android.support.v7.widget.LinearLayoutManager;

import com.andy.baselibrary.dialog.DataBindBaseDialog;
import com.andy.infrastructure.R;
import com.andy.infrastructure.adapter.MenuMaterialAdapter;
import com.andy.infrastructure.bean.DemoFragmentBean;
import com.andy.infrastructure.databinding.DlgMaterialMenuBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andy on 2017/3/8.
 */

public class MaterialMenuDialog extends DataBindBaseDialog {
    @Override
    protected int getLayoutId() {
        return R.layout.dlg_material_menu;
    }

    @Override
    protected int getStyle() {
        return R.style.BaseDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        MenuMaterialAdapter adapter = new MenuMaterialAdapter(mContext, (Material1Activity) getActivity());

        DlgMaterialMenuBinding mDataBind = (DlgMaterialMenuBinding) dataBinding;
        mDataBind.rclMaterialMenu.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBind.rclMaterialMenu.setAdapter(adapter);

        adapter.initData(intiDataSet());
    }

    /**
     *
     */
    private List<DemoFragmentBean> intiDataSet() {
        List<DemoFragmentBean> menuList = new ArrayList<>();

        menuList.add(new DemoFragmentBean()
        .setClassName(TextInputlayoutFragment.class)
        .setName("TextInputlayoutFragment")
        .setDesc("TextInputlayout Fragment"));

        menuList.add(new DemoFragmentBean()
                .setClassName(SimpleDesighWidgetFragment.class)
                .setName("SimpleDesighWidgetFragment")
                .setDesc("SimpleDesighWidgetFragment"));

        return menuList;
    }
}
