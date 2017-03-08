package com.andy.infrastructure.adapter;

import android.content.Context;
import android.view.View;

import com.andy.baselibrary.adapter.DataBindRecyclerAdapter;
import com.andy.baselibrary.holder.DataBindRecyclerHolder;
import com.andy.infrastructure.R;
import com.andy.infrastructure.bean.DemoFragmentBean;
import com.andy.infrastructure.holder.MenuMaterialHolder;
import com.andy.infrastructure.presenter.ItemMenuMaterialPresenter;


/**
 * Created by Andy on 2017/3/8.
 */

public class MenuMaterialAdapter extends DataBindRecyclerAdapter<DemoFragmentBean> {
    private ItemMenuMaterialPresenter presenter;

    public MenuMaterialAdapter(Context mContext, ItemMenuMaterialPresenter presenter) {
        super(mContext);
        this.presenter = presenter;
    }

    @Override
    public DataBindRecyclerHolder<DemoFragmentBean> getHolder(View itemView) {
        return new MenuMaterialHolder(itemView, presenter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_menu_material;
    }
}
