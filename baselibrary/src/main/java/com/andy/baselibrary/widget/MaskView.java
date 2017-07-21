package com.andy.baselibrary.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Andy on 2017/7/21.
 * use to disable touch event on other components.
 */

public class MaskView extends LinearLayout {
    private boolean doMask = false;

    public MaskView(Context context) {
        super(context);
    }

    public MaskView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MaskView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        /**
         * if doMask is true intercept all touch event,
         */
        if (doMask) {
            return true;
        } else {
            return super.onInterceptTouchEvent(event);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (doMask) {
            return true;
        } else {
            return super.onTouchEvent(event);
        }
    }

    public void setDoMask(boolean doMask) {
        this.doMask = doMask;
    }
}
