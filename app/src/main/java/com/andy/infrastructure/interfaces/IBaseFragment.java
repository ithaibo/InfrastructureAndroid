package com.andy.infrastructure.interfaces;

/**
 * Created by Andy on 2017/3/7.
 */

public interface IBaseFragment {
    void showProgressDialog();
    void hidProgressDialog();
    void showError(String error);
}
