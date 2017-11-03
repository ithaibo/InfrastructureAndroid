package com.ithaibo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Smily on 2017/10/29.
 */

public class Bezier extends View {
	private Paint mPaint;
	private int centerX, centerY;

	private PointF start, end, control, control_2;
	private boolean consumedByControl_1 = false;
	private boolean consumedByControl_2 = false;

	public Bezier(Context context) {
		super(context);
		init();
	}

	public Bezier(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public Bezier(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		mPaint = new Paint();
		mPaint.setColor(Color.BLACK);
		mPaint.setAntiAlias(true);
		mPaint.setStrokeWidth(8);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setTextSize(60);

		start = new PointF(0, 0);
		end = new PointF(0, 0);
		control = new PointF(0, 0);
		control_2 = new PointF(0, 0);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		centerX = w / 2;
		centerY = h / 2;

		// 初始化数据点和控制点的位置
		start.x = centerX - 200;
		start.y = centerY;
		end.x = centerX + 200;
		end.y = centerY;
		control.x = centerX - 100;
		control.y = centerY - 100;
		control_2.x = centerX + 100;
		control_2.y = centerY + 100;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 根据触摸位置更新控制点，并提示重绘
		float x = event.getX();
		float y = event.getY();
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			consumedByControl_1 = Math.abs(x - control.x) < 20 && Math.abs(y - control.y) < 20;
			consumedByControl_2 = Math.abs(x - control_2.x) < 20 && Math.abs(y - control_2.y) < 20;

			if (consumedByControl_1 || consumedByControl_2) {
				return true;
			}
		} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			if (consumedByControl_1) {
				control.x = event.getX();
				control.y = event.getY();
				invalidate();
				return true;
			} else {
				control_2.x = event.getX();
				control_2.y = event.getY();
				invalidate();
				return true;
			}
		}
		consumedByControl_1 = false;
		consumedByControl_2 = false;
		return super.onTouchEvent(event);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// 绘制数据点和控制点
		mPaint.setColor(Color.GRAY);
		mPaint.setStrokeWidth(20);
		canvas.drawPoint(start.x, start.y, mPaint);
		canvas.drawPoint(end.x, end.y, mPaint);
		canvas.drawPoint(control.x, control.y, mPaint);
		canvas.drawPoint(control_2.x, control_2.y, mPaint);

		// 绘制辅助线
		mPaint.setStrokeWidth(4);
		canvas.drawLine(start.x, start.y, control.x, control.y, mPaint);
		canvas.drawLine(control.x, control.y, control_2.x, control_2.y, mPaint);
		canvas.drawLine(control_2.x, control_2.y, end.x, end.y, mPaint);

		// 绘制贝塞尔曲线
		mPaint.setColor(Color.RED);
		mPaint.setStrokeWidth(8);

		Path path = new Path();

		path.moveTo(start.x, start.y);
		path.cubicTo(control.x, control.y,control_2.x, control_2.y, end.x, end.y);

		canvas.drawPath(path, mPaint);
	}
}
