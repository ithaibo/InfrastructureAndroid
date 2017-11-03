package com.ithaibo.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ithaibo.module_view.R;

/**
 * Created by Andy on 2017/11/3.
 */

public class MatrixPolyView extends View {

    private Bitmap mBitmap;
    private Matrix mMatrix;
    private Paint mPaint;
    private float[] src = new float[8];
    private float[] dst = new float[8];
    private int pointCount = 3;
    private int triggerRadius = 180;

    public MatrixPolyView(Context context) {
        super(context);
        init(context, null);
    }

    public MatrixPolyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MatrixPolyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(21)
    public MatrixPolyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.custom_view_home);

        float[] temp = new float[]{0, 0,
                mBitmap.getWidth(), 0,
                mBitmap.getWidth(), mBitmap.getHeight(),
                0, mBitmap.getHeight()};
        src = temp.clone();
        dst = temp.clone();

        mMatrix = new Matrix();
        mMatrix.setPolyToPoly(src, 0, dst, 0, 4);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(100,100);

        mPaint.setColor(0xff252525);
        mPaint.setStrokeWidth(2);

        //draw line
        canvas.drawLine(0,0,getMeasuredWidth(), 0, mPaint);
        canvas.drawLine(0,0,0, getMeasuredHeight(), mPaint);

        canvas.drawBitmap(mBitmap, mMatrix, null);

        //draw points
        mPaint.setStrokeWidth(50);
        mPaint.setColor(0xffd19165);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        float[] dst = new float[8];
        mMatrix.mapPoints(dst, src);
        for (int i=0; i<pointCount*2; i+=2) {
            canvas.drawPoint(dst[i], dst[i+1], mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < pointCount * 2; i += 2) {
                    if (Math.abs(event.getX() - dst[i]) <= triggerRadius &&
                            Math.abs(event.getY() - dst[i + 1]) <= triggerRadius) {
                        dst[i] = event.getX() - 100;
                        dst[i + 1] = event.getY() - 100;
                        break;  // 防止两个点的位置重合
                    }
                }
                resetMatrix(pointCount);
                invalidate();
                break;
        }
        return true;
    }

    public void resetMatrix(int pointCount) {
        mMatrix.reset();
        mMatrix.setPolyToPoly(src, 0, dst, 0, pointCount);
    }

    public void setPointCount(int pointCount) {
        this.pointCount = pointCount >4 || pointCount< 0? 4: pointCount;
        dst = src.clone();
        resetMatrix(this.pointCount);
        invalidate();
    }


}
