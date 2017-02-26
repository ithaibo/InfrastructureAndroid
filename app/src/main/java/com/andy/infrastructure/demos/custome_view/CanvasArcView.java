package com.andy.infrastructure.demos.custome_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.andy.infrastructure.R;

/**
 * Created by Andy on 2017/2/20.
 */

public class CanvasArcView extends View {

    private static final int minWidth = 100;
    private static final int minHeight= 100;

    private Paint arcPaint;
    private RectF mRectF;

    public CanvasArcView(Context context) {
        super(context);
        init();
    }

    public CanvasArcView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        arcPaint = new Paint();
        arcPaint.setColor(getResources().getColor(R.color.colorPrimary));
        arcPaint.setAntiAlias(true);

        mRectF = new RectF(0, 0, 150, 150);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthMeasureSize = MeasureSpec.getMode(widthMeasureSpec);

        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightMeasureSize = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMeasureMode == MeasureSpec.AT_MOST &&
                heightMeasureMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(minWidth, minHeight);
        } else if (widthMeasureMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthMeasureSize, minHeight);
        } else if (heightMeasureMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(minWidth, heightMeasureSize);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawArc(mRectF, 0, 60, true, arcPaint);
    }
}
