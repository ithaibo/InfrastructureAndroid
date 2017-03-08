package com.andy.infrastructure.holder;

import android.view.View;

import com.andy.baselibrary.holder.DataBindRecyclerHolder;
import com.andy.infrastructure.bean.DemoFragmentBean;
import com.andy.infrastructure.demos.material.MenuDataBind;
import com.andy.infrastructure.presenter.ItemMenuMaterialPresenter;

/**
 * Created by Andy on 2017/3/8.
 */

public class MenuMaterialHolder extends DataBindRecyclerHolder<DemoFragmentBean> {
    private ItemMenuMaterialPresenter presenter;
    public MenuMaterialHolder(View itemView, ItemMenuMaterialPresenter presenter) {
        super(itemView);
        this.presenter = presenter;
    }

    @Override
    public void bind(DemoFragmentBean data) {
        MenuDataBind menuDataBind = (MenuDataBind) mBind;

        menuDataBind.setDescMenu(data);
        menuDataBind.setItemMenuPresenter(this.presenter);
    }
}
