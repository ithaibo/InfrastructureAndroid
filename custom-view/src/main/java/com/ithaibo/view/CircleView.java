package com.ithaibo.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.andy.baselibrary.utils.LogUtil;
import com.ithaibo.module_view.R;

/**
 * Created by Smily on 2017/4/3.
 */

public class CircleView extends View {
    private int colorCircle;
    private float radius;
    /**default black color*/
    private final int colorCircleDefault = Color.BLACK;
    private final float radiusDefault = 50.0f;
    private Paint paintCircle;

    /**默认宽高*/
    private int defaultWidth = 100;
    private int defaultHeight= 100;

    public CircleView(Context context) {
        super(context);
        init(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        paintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        if (attrs!=null) {
            colorCircle = a.getInt(R.styleable.CircleView_circle_color, colorCircleDefault);
            radius = a.getFloat(R.styleable.CircleView_circle_radius, radiusDefault);
            a.recycle();
        }
        paintCircle.setColor(colorCircle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**获取宽高Mode\Size*/
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        /**处理宽、高位wrap_content的情况*/
        int mWidth = widthMode == MeasureSpec.AT_MOST ? defaultWidth : widthSize;
        int mHeight = heightMode == MeasureSpec.AT_MOST ? defaultHeight : heightSize;

        int len = Math.max(mWidth, mHeight);

        setMeasuredDimension(len, len);

        LogUtil.d("width: " + mWidth);
        LogUtil.d("Height: " + mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        LogUtil.d("paddingLeft: " + getPaddingLeft());
        LogUtil.d("paddingRight: " + getPaddingRight());

        LogUtil.d("width: " + getWidth());
        LogUtil.d("Height: " + getHeight());

        int mWidth = getWidth() - paddingLeft -paddingRight;
        int mHeight = getHeight() - paddingTop - paddingBottom;

        LogUtil.d("width: " + mWidth);
        LogUtil.d("Height: " + mHeight);

        float radiusNew = Math.min(mWidth/2, mHeight/2);
        LogUtil.d("radiusNew: " + radiusNew);

        canvas.drawCircle(paddingLeft + mWidth/2, paddingTop + mHeight/2, radiusNew, paintCircle);
    }
}
