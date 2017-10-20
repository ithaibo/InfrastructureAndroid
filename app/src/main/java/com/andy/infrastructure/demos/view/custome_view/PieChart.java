package com.andy.infrastructure.demos.view.custome_view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andy on 2017/10/16.
 */

public class PieChart extends View {
    private int mWidth = 200;
    private int mHeight = 200;
    private List<PieData> mDataList = new LinkedList<>();
    private float mTotalValue;
    private float mCurrentStartAngle;   //当前可绘起始角度

    private int currentRed;     //用于计算颜色
    private int currentGreen;   //用于计算颜色
    private int currentBlue;    //用于计算颜色

    public PieChart(Context context) {
        super(context);
    }

    public PieChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PieChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public PieChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);

        canvas.translate(mWidth/2, mHeight/2);

        canvas.drawCircle(0, 0, mWidth/2, paint);
        canvas.drawCircle(0,0, mWidth/2 -20, paint);
        for (int i=0; i<360; i+=30) {
            canvas.drawLine(0, mWidth/2 -20, 0, mWidth/2, paint);
            canvas.rotate(30);
        }

        float radius = Math.min(mWidth - getPaddingLeft() - getPaddingRight(), mHeight - getPaddingTop() - getPaddingBottom()) /2;
        RectF rectF = new RectF(-radius, -radius, radius, radius);
        paint.setStyle(Paint.Style.FILL);
        for (int i=0; i<mDataList.size(); i++) {
            PieData item = mDataList.get(i);
            paint.setColor(item.color);
            drawItem(canvas, rectF, paint, item);
        }
    }

    private void drawItem(Canvas canvas, RectF rectF, Paint paint, PieData pieData) {
        canvas.drawArc(rectF, mCurrentStartAngle, pieData.angle, true, paint);
        mCurrentStartAngle +=pieData.angle;
    }

    public void setDataList(List<PieData> dataList) {
        if (dataList!=null) {
            this.mDataList = dataList;
            onDataSetChanged();
            invalidate();
        }
    }

    private void onDataSetChanged() {
        //calculate total value
        if (mDataList == null || mDataList.size()<=0) {
            return;
        }

        for (PieData item : mDataList) {
            mTotalValue +=item.value;
        }

        for (int i=0; i<mDataList.size(); i++) {
            PieData item = mDataList.get(i);
            item.angle = item.value / mTotalValue * 360;

            switch (i%3) {
                case 0:
                    currentRed =(150+ (currentRed + 20)) % 255;
                    break;
                case 1:
                    currentGreen = (150+ (currentRed + 20)) % 255;
                    break;
                case 2:
                    currentBlue = (150+ (currentRed + 20)) % 255;
                    break;
            }
            item.color = Color.rgb(currentRed, currentGreen, currentBlue);
        }
    }

    public static class PieData {
        private float value;
        private String label;
        private float angle;
        private int color;

        public PieData(float value, String label) {
            this.value = value;
            this.label = label;
        }

        public PieData() {
        }

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}
