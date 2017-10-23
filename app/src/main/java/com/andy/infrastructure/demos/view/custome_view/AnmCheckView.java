package com.andy.infrastructure.demos.view.custome_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.andy.infrastructure.R;

/**
 * Created by Andy on 2017/10/16.
 */

public class AnmCheckView extends View {
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private Bitmap okBitmap;
    private Handler mHandler;
    private boolean checked;

    private final int COUNT_PAGE = 13;   //动画帧数
    private int currentIndex = -1;   //当前动画帧index

    private final int MSG_WHAT_NEXT_PAGE = 00001;
    private final int MSG_WHAT_PRE_PAGE =  00010;

    private boolean anmRunning = false;

    //color
    private int colorUnChecked = 0xffff5317;
    private int colorChecked = 0xff34d058;

    public AnmCheckView(Context context) {
        super(context);
        init(context);
    }

    public AnmCheckView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(colorUnChecked);
        mPaint.setAntiAlias(true);

        okBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_check_marks);

        mHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (currentIndex >=0 && currentIndex < COUNT_PAGE) {
                    invalidate();
                    if(msg.what == MSG_WHAT_NEXT_PAGE) {
                        //下一页
                        currentIndex++;
                        sendEmptyMessageDelayed(MSG_WHAT_NEXT_PAGE, 50);
                    } else if (msg.what == MSG_WHAT_PRE_PAGE) {
                        //上一页
                        currentIndex--;
                        sendEmptyMessageDelayed(MSG_WHAT_PRE_PAGE, 50);
                    }
                } else {
                    //已经没有了
                    if (checked) {
                        currentIndex = COUNT_PAGE -1;
                    } else {
                        currentIndex = -1;
                    }

                    invalidate();
                    anmRunning = false;
                }
            }
        };
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
        //handle padding
        canvas.translate(mWidth/2, mHeight/2);
        mPaint.setColor(checked? colorChecked : colorUnChecked);
        canvas.drawCircle(0, 0, mWidth/2, mPaint);

        int imgLength = okBitmap.getWidth();
        int imgHeight = okBitmap.getHeight();
        int itemWidth = imgLength / 13;

        Rect src = new Rect(itemWidth * currentIndex, 0, itemWidth*(1+currentIndex), imgHeight);
        RectF dst = new RectF(-(mWidth/2 -20), -(mWidth/2-20), mWidth/2-20, mWidth/2-20);

        canvas.drawBitmap(okBitmap, src, dst, null);
    }

    public void check() {
        if (anmRunning || checked) {
            return;
        }
        currentIndex = 0;
        mHandler.sendEmptyMessageDelayed(MSG_WHAT_NEXT_PAGE, 50);
        anmRunning = true;
        checked = true;
    }

    public void unCheck() {
        if (anmRunning || !checked) {
            return;
        }
        currentIndex = COUNT_PAGE - 1;
        mHandler.sendEmptyMessageDelayed(MSG_WHAT_PRE_PAGE, 50);
        checked = false;
        anmRunning = true;
    }

    public Boolean getChecked() {
        return checked;
    }
}
