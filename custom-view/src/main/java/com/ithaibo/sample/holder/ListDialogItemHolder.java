package com.ithaibo.sample.holder;

import android.view.View;

import com.andy.baselibrary.holder.DataBindRecyclerHolder;
import com.andy.infrastructure.ListDialogItemBind;
import com.ithaibo.sample.bean.ListDialogItemDataBean;

/**
 * Created by andy on 17-3-18.
 */

public class ListDialogItemHolder<T> extends DataBindRecyclerHolder<ListDialogItemDataBean<T>> {

    private final ListDialogItemBind dialogItemBind;

    public ListDialogItemHolder(View itemView) {
        super(itemView);
        dialogItemBind = (ListDialogItemBind) mBind;
    }


    @Override
    public void bind(ListDialogItemDataBean data) {
        dialogItemBind.setItemData(data);
    }
}
