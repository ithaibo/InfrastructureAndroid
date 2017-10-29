package com.ithaibo.sample.adapter;

import android.content.Context;
import android.view.View;

import com.andy.baselibrary.adapter.DataBindRecyclerAdapter;
import com.andy.baselibrary.holder.DataBindRecyclerHolder;
import com.ithaibo.module_view.R;
import com.ithaibo.sample.bean.ListDialogItemDataBean;
import com.ithaibo.sample.holder.ListDialogItemHolder;

/**
 * Created by andy on 17-3-18.
 */

public class ListDialogAdapter<T> extends DataBindRecyclerAdapter<ListDialogItemDataBean<T>> {

    public ListDialogAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public DataBindRecyclerHolder getHolder(View itemView) {
        return new ListDialogItemHolder<T>(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_list_dialog_layout;
    }
}
