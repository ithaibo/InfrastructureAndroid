package com.andy.baselibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.andy.baselibrary.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Andy on 2016/9/26.
 */

public abstract class BaseRecyclerdapter<T> extends RecyclerView.Adapter {
    protected Context mContext;
    protected LayoutInflater mLayoutInflater;
    private List<T> dataList;

    public BaseRecyclerdapter(Context mContext) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void initData(List<T> dataList) {
        refreshData(dataList);
    }

    public void refreshData(List<T> dataList){
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public void appendData(List<T> dataList){
        if (CommonUtils.isListEmpty(this.dataList)){
            this.dataList = new ArrayList<>();
        }
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return CommonUtils.isListEmpty(dataList)? 0 : dataList.size();
    }

    public T getItem(int position){
        return CommonUtils.isListEmpty(dataList)? null : dataList.get(position);
    }
}
