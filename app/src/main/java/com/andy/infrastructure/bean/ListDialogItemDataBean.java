package com.andy.infrastructure.bean;

import com.andy.infrastructure.demos.web.ListDialogItemPresenter;

/**
 * Created by andy on 17-3-18.
 */

public class ListDialogItemDataBean<T> extends Bean
{
    private T itemData;
    private String itemDesc;
    private ListDialogItemPresenter<T> presenter;

    public T getItemData() {
        return itemData;
    }

    public ListDialogItemDataBean<T> setItemData(T itemData) {
        this.itemData = itemData;
        return this;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public ListDialogItemDataBean<T> setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
        return this;
    }

    public ListDialogItemPresenter<T> getPresenter() {
        return presenter;
    }

    public ListDialogItemDataBean<T> setPresenter(ListDialogItemPresenter<T> presenter) {
        this.presenter = presenter;
        return this;
    }

    @Override
    public String toString() {
        return "ListDialogItemDataBean{" +
                "itemData=" + itemData +
                ", itemDesc='" + itemDesc + '\'' +
                '}';
    }
}
