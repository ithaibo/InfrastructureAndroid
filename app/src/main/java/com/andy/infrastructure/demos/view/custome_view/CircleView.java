package com.andy.infrastructure.demos.view.custome_view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.andy.infrastructure.R;

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

    private int mWidth;
    private int mHeight;

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

        int heightMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(widthMeasureSpec);

        /**处理宽、高位wrap_content的情况*/
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            mWidth = defaultWidth;
            mHeight = defaultHeight;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            mWidth = defaultWidth;
            mHeight= heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            mWidth = widthSize;
            mHeight= defaultHeight;
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mWidth = getWidth();
        mHeight = getHeight();

        int radiusNew = Math.min(mWidth/2, mHeight/2);
        radius = Math.min(radiusNew, radius);

        canvas.drawCircle(mWidth/2, mHeight/2, radius, paintCircle);
    }
}
