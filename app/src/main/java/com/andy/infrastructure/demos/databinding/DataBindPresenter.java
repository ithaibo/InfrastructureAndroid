package com.andy.infrastructure.demos.databinding;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;


/**
 * Created by Andy on 2017/1/5.
 */

public class DataBindPresenter {
    private Context mContext;
    private Customer mUser;

    public DataBindPresenter( Context mContext) {
        this.mContext = mContext;
    }

    public void getEmail(String email) {
        if (!TextUtils.isEmpty(email)){
            Toast.makeText(mContext, email, Toast.LENGTH_LONG).show();
        }
    }

    public void modifyMobileValue() {
        if (mUser != null) {
            this.mUser.setMobile("18966669999");
        }
    }

    public Customer getmUser() {
        return mUser;
    }

    public void setmUser(Customer mUser) {
        this.mUser = mUser;
    }
}
