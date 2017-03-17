package com.andy.infrastructure.demos.view;

import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Scroller;

import com.andy.baselibrary.activity.BaseActivity;
import com.andy.baselibrary.utils.LogUtil;
import com.andy.infrastructure.R;
import com.andy.infrastructure.databinding.ActViewBasicBinding;

/**
 * Created by Andy on 2017/3/17.
 */

public class ViewBasicActivity extends BaseActivity {
    private GestureDetector.OnGestureListener gestureListener;
    private GestureDetectorCompat gestureDetector;
    private Scroller scroller;
    private ActViewBasicBinding binding;

    @Override
    protected int getLayoutId() {
        return R.layout.act_view_basic;
    }

    @Override
    protected void initData() {
        gestureDetector = new GestureDetectorCompat(this, getGestureListener());
    }

    @Override
    protected void initViews() {
        binding = (ActViewBasicBinding) mDataBind;
        binding.tv.setEnabled(false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.d("有新的触摸事件产生");
        this.gestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    private GestureDetector.OnGestureListener getGestureListener() {
        if (gestureListener == null) {
            gestureListener = new GestureDetector.OnGestureListener(){
                @Override
                public boolean onDown(MotionEvent e) {
                    LogUtil.i("onDown: " + e.toString());
                    return true;
                }

                @Override
                public void onShowPress(MotionEvent e) {
                    LogUtil.i("onShowPress: " + e.toString());
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    LogUtil.i("onSingleTapUp: " + e.toString());
                    return true;
                }

                @Override
                public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                    LogUtil.i("onScroll: " + e1.toString() + "--" + e2.toString() + "--distanceX " + distanceX + "--distanceY " +distanceY );
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    LogUtil.i("onLongPress: " + e.toString());
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    LogUtil.i("onFling: " + e1.toString() + "--" + e2.toString() + "--velocityX " + velocityX + "--distanceY " +velocityY);
                    return false;
                }
            };
        }
        return gestureListener;
    }


}
