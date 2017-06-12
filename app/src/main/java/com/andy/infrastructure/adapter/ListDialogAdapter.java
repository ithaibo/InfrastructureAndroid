package com.andy.infrastructure.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.andy.baselibrary.adapter.BaseRecyclerViewHolder;
import com.andy.baselibrary.adapter.DataBindRecyclerAdapter;
import com.andy.baselibrary.holder.DataBindRecyclerHolder;
import com.andy.infrastructure.R;
import com.andy.infrastructure.bean.ListDialogItemDataBean;
import com.andy.infrastructure.holder.ListDialogItemHolder;

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
