package com.ithaibo.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.Scroller;

import com.andy.baselibrary.utils.LogUtil;

/**
 * Created by andy on 17-4-4.
 */

public class HorizontalScrollViewEx extends ViewGroup {
    /**当前的Child的个数*/
    private int mChildCount;
    private int mChildWidth;
    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;

    private int mLastXIntercepted = 0;
    private int mLastYIntercepted = 0;
    private int mLastX;
    private int mLastY;
    private int mChildIndex;

    public HorizontalScrollViewEx(Context context) {
        super(context);
        init();
    }

    public HorizontalScrollViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HorizontalScrollViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (mScroller == null) {
            mScroller = new Scroller(getContext());
            mVelocityTracker = VelocityTracker.obtain();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();

        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else {
            //处理wrap_content的情况

            measureChildren(widthMeasureSpec, heightMeasureSpec);

            int widthMode = MeasureSpec.getMode(widthMeasureSpec);
            int heightMode= MeasureSpec.getMode(heightMeasureSpec);
            int widthSize = MeasureSpec.getSize(widthMeasureSpec);
            int heightSize= MeasureSpec.getSize(heightMeasureSpec);

            int width = widthMode == MeasureSpec.AT_MOST?
                    getChildAt(0).getMeasuredWidth() * childCount: widthSize;
            int height= heightMode== MeasureSpec.AT_MOST?
                    getChildAt(0).getMeasuredHeight(): heightSize;

            setMeasuredDimension(width, height);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        /**这里需要处理margin，padding*/
        int childLeft = 0;
        mChildCount = getChildCount();

        for (int i=0; i<mChildCount; i++) {
            final View childItemView = getChildAt(i);
            if (childItemView.getVisibility() !=View.GONE) {
                final int currentChildWidth = childItemView.getMeasuredWidth();
                mChildWidth = currentChildWidth;
                childItemView.layout(childLeft, 0, childLeft + currentChildWidth, childItemView.getMeasuredHeight());
                childLeft += currentChildWidth;
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;

        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                intercepted = false;
                if (mScroller.isFinished()) {
                    intercepted = true;
                    mScroller.abortAnimation();
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastXIntercepted;
                int deltaY = y - mLastYIntercepted;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    intercepted = true;
                } else {
                    intercepted = false;
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                intercepted = false;
                break;
            }
        }

        LogUtil.i("intercepted: " + intercepted);

        mLastX = x;
        mLastY = y;

        mLastXIntercepted = x;
        mLastYIntercepted = y;

        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                scrollBy(-deltaX, 0);
                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = mVelocityTracker.getXVelocity();
                if (Math.abs(xVelocity) >= 50) {
                    mChildIndex = xVelocity > 0? mChildIndex -1 : mChildIndex +1;
                } else {
                    mChildIndex = (scrollX + mChildWidth /2)/mChildWidth;
                }

                mChildIndex = Math.max(0, Math.min(mChildIndex, mChildCount - 1));
                int dx = mChildIndex * mChildWidth - scrollX;
                smoothScrollBy(dx, 0);
                mVelocityTracker.clear();
                break;
        }

        mLastX = x;
        mLastY = y;

        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }

    private void smoothScrollBy(int dx, int dy) {
        mScroller.startScroll(getScrollX(), 0, dx, dy, 500);
        invalidate();
    }

    @Override
    protected void onDetachedFromWindow() {
        mVelocityTracker.clear();
        super.onDetachedFromWindow();
    }
}
