package com.andy.baselibrary.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.andy.baselibrary.utils.LogUtil;

/**
 * Created by Andy on 2016/12/19.
 * <ol>
 * you must implements:
 * <li>getFrgLayoutId()</li>
 * <li></li>
 * <li></li>
 * <li></li>
 * <li></li>
 * </ol>
 */

public abstract class BaseFragment extends Fragment {
    protected Activity mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = (Activity) context;
        LogUtil.v(this.getClass().getName() + "onAttach.");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtil.v(this.getClass().getName() + "onCreate.");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.v(this.getClass().getName() + "onCreateView.");


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 获取当前Fragment布局文件Id
     * @return id of layout.
     */
    protected abstract int getFrgLayoutId();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.v(this.getClass().getName() + "onActivityCreated.");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        LogUtil.v(this.getClass().getName() + "onViewStateRestored.");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.v(this.getClass().getName() + "onStart.");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.v(this.getClass().getName() + "onResume.");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.v(this.getClass().getName() + "onPause.");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.v(this.getClass().getName() + "onStop.");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.v(this.getClass().getName() + "onDestroyView.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.v(this.getClass().getName() + "onDestroy.");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.v(this.getClass().getName() + "onDetach.");
    }

    protected void showToast(String toastStr) {
        showToast(toastStr, Toast.LENGTH_SHORT);
    }

    protected void showToastLong(String toastStr) {
        showToast(toastStr, Toast.LENGTH_LONG);
    }

    private void showToast(String toastStr, int style ) {
        Toast.makeText(mContext, toastStr, style).show();
    }
}
