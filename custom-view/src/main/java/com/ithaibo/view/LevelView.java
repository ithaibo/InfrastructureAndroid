package com.ithaibo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Andy on 2017/10/23.
 */

public class LevelView extends View {
    private String content = "text on path.";
    private Paint paint;
    private Path path;

    public LevelView(Context context) {
        super(context);
        init();
    }

    public LevelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LevelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.GRAY);

        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.i("PathTextView", "Width: " + getMeasuredWidth() + ", Height: " + getMeasuredHeight());

        canvas.translate(getMeasuredWidth()/2, getMeasuredHeight()/2);

        int maxR = getMeasuredWidth()/2 - 20;

        for (int i = 5; i>0; i--) {
            int tmpR = maxR * i /5;
            //out ring[level 5]
            path.moveTo(-tmpR, 0); //left
            path.lineTo(-tmpR * (float) Math.cos(1.0d / 3 * Math.PI), tmpR * (float) Math.sin(1.0d / 3 * Math.PI));    //left top
            path.lineTo(tmpR * (float) Math.cos(1.0d / 3 * Math.PI), tmpR * (float) Math.sin(1.0d / 3 * Math.PI));    //right top
            path.lineTo(tmpR , 0);    //right
            path.lineTo(tmpR * (float) Math.cos(1.0d / 3 * Math.PI), -tmpR * (float) Math.sin(1.0d / 3 * Math.PI));    //right bottom
            path.lineTo(-tmpR * (float) Math.cos(1.0d / 3 * Math.PI), -tmpR * (float) Math.sin(1.0d / 3 * Math.PI));    //left bottom
            path.close();
        }

        //draw diagonal horizontal
        path.moveTo(-maxR, 0);
        path.lineTo(maxR, 0);

        //draw diagonal lefTop to rightBottom
        path.moveTo(-maxR * (float) Math.cos(1.0d / 3 * Math.PI), maxR * (float) Math.sin(1.0d / 3 * Math.PI));
        path.lineTo(maxR * (float) Math.cos(1.0d / 3 * Math.PI), -maxR * (float) Math.sin(1.0d / 3 * Math.PI));

        path.moveTo(-maxR * (float) Math.cos(1.0d / 3 * Math.PI), -maxR * (float) Math.sin(1.0d / 3 * Math.PI));
        path.lineTo(maxR * (float) Math.cos(1.0d / 3 * Math.PI), maxR * (float) Math.sin(1.0d / 3 * Math.PI));

        canvas.drawPath(path, paint);

    }

    public void setContent(String content) {
        this.content = content;
        invalidate();
    }

    public String getContent() {
        return content;
    }

    private class Point {
        int x;
        int y;
        private int r;

    }
}
