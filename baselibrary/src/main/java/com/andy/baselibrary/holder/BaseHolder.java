package com.andy.baselibrary.holder;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Andy on 2016/11/15.
 */

public class BaseHolder {
    public BaseHolder(View rootView) {
        ButterKnife.bind(this, rootView);
    }
}
