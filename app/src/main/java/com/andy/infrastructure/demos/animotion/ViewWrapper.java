package com.andy.infrastructure.demos.animotion;

import android.view.View;

/**
 * Created by Andy on 2017/2/6.
 */

public class ViewWrapper {
    private View mTarget;

    public ViewWrapper(View target) {
        this.mTarget = target;
    }

    public int getWidth() {
        return mTarget.getLayoutParams().width;
    }

    public void setWidth(int wPix) {
        mTarget.getLayoutParams().width = wPix;
        mTarget.requestLayout();
    }
}
