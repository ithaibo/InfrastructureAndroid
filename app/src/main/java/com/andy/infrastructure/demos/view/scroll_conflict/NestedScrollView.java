package com.andy.infrastructure.demos.view.scroll_conflict;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Andy on 2017/9/11.
 */

public class NestedScrollView extends ScrollView {
    public NestedScrollView(Context context) {
        super(context);
    }

    public NestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NestedScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        int[] rawPoint = new int[2];
        getLocationOnScreen(rawPoint);

        float evRawX = ev.getRawX();
        float evRawY = ev.getRawY();

        float left = rawPoint[0];
        float right = left + getMeasuredWidth();
        float top = rawPoint[1];
        float bottom = top + getMeasuredHeight();

        if (left <= evRawX && evRawX <= right &&
                top <= evRawY && bottom <= bottom) {
            getParent().requestDisallowInterceptTouchEvent(true);
            return true;
        } else {
            return super.onInterceptTouchEvent(ev);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            return true;
//        } else {
            if (ev.getAction() == MotionEvent.ACTION_MOVE) {
                int[] rawPoint = new int[2];
                getLocationOnScreen(rawPoint);

                float evRawX = ev.getRawX();
                float evRawY = ev.getRawY();

                float left = rawPoint[0];
                float right = left + getMeasuredWidth();
                float top = rawPoint[1];
                float bottom = top + getMeasuredHeight();

                if (left<= evRawX && evRawX <=right &&
                        top <=evRawY && bottom <= bottom) {
                    return true;
                } else {
                    return super.onTouchEvent(ev);
                }
            } else {
                return super.onTouchEvent(ev);
            }
//        }

    }
}
