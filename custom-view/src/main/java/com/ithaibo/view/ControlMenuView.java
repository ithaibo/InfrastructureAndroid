package com.ithaibo.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by Smily on 2017/11/5.
 */

public class ControlMenuView extends View {
	private int mWidth;
	private int mHeight;

	private Path center_path;
	private Path up_path;
	private Path left_path;
	private Path right_path;
	private Path down_path;

	private Region center_region;
	private Region left_region;
	private Region up_region;
	private Region right_region;
	private Region down_region;

	private Paint mPaint;

	private int DEFAULT_COLOR = 0xFF4D5266;
	private int PRESSED_COLOR = 0xFFDE9D81;
	private Matrix mMatrix;

	private final int INDEX_CENTER = 0;
	private final int INDEX_LEFT = 1;
	private final int INDEX_UP = 2;
	private final int INDEX_RIGHT = 3;
	private final int INDEX_DOWN = 4;
	private int currentIndex = -1;
	private int touchIndex = -1;

	public ControlMenuView(Context context) {
		super(context);
		init();
	}

	public ControlMenuView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ControlMenuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	@TargetApi(21)
	public ControlMenuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mWidth = w;
		mHeight = h;

		mMatrix.reset();

		Region globalRegion = new Region(-w, -h, w, h);
		int minWidth = w > h ? h : w;
		minWidth *= 0.8;

		int br = minWidth / 2;
		RectF bigCircle = new RectF(-br, -br, br, br);

		int sr = minWidth / 4;
		RectF smallCircle = new RectF(-sr, -sr, sr, sr);

		float bigSweepAngle = 84;
		float smallSweepAngle = -80;

		center_path.addCircle(0, 0, 0.2f * minWidth, Path.Direction.CW);
		center_region.setPath(center_path, globalRegion);

		left_path.addArc(bigCircle, 140, bigSweepAngle);
		left_path.arcTo(smallCircle, 220, smallSweepAngle);
		left_path.close();
		left_region.setPath(left_path, globalRegion);

		up_path.addArc(bigCircle, 230, bigSweepAngle);
		up_path.arcTo(smallCircle, 310, smallSweepAngle);
		up_path.close();
		up_region.setPath(up_path, globalRegion);

		right_path.addArc(bigCircle, -40, bigSweepAngle);
		right_path.arcTo(smallCircle, 40, smallSweepAngle);
		right_path.close();
		right_region.setPath(right_path, globalRegion);

		down_path.addArc(bigCircle, 50, bigSweepAngle);
		down_path.arcTo(smallCircle, 130, smallSweepAngle);
		down_path.close();
		down_region.setPath(down_path, globalRegion);

	}

	private void init() {
		left_path = new Path();
		up_path = new Path();
		right_path = new Path();
		down_path = new Path();
		center_path = new Path();

		left_region = new Region();
		up_region = new Region();
		right_region = new Region();
		down_region = new Region();
		center_region = new Region();

		mPaint = new Paint();
		mPaint.setAntiAlias(true);

		mMatrix = new Matrix();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.translate(mWidth/2, mHeight/2);

		if (mMatrix.isIdentity()) {
			canvas.getMatrix().invert(mMatrix);
		}

		mPaint.setColor(DEFAULT_COLOR);
		canvas.drawPath(center_path, mPaint);
		canvas.drawPath(left_path, mPaint);
		canvas.drawPath(up_path, mPaint);
		canvas.drawPath(right_path, mPaint);
		canvas.drawPath(down_path, mPaint);

		drawCheckedRegion(canvas);
	}

	private void drawCheckedRegion(Canvas canvas) {
		mPaint.setColor(PRESSED_COLOR);
		switch (currentIndex) {
			case INDEX_LEFT:
				canvas.drawPath(left_path, mPaint);
				break;
			case INDEX_UP:
				canvas.drawPath(up_path, mPaint);
				break;
			case INDEX_RIGHT:
				canvas.drawPath(right_path, mPaint);
				break;
			case INDEX_DOWN:
				canvas.drawPath(down_path, mPaint);
				break;
			case  INDEX_CENTER:
				canvas.drawPath(center_path, mPaint);
				break;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float[] pts = new float[2];
		pts[0] = event.getRawX();
		pts[1] = event.getRawY();

		mMatrix.mapPoints(pts);

		int x = (int) pts[0];
		int y = (int) pts[1];

		switch (event.getActionMasked()) {
			case MotionEvent.ACTION_DOWN:
				currentIndex = touchIndex = getTouchedIndex(x,y);
				break;
			case MotionEvent.ACTION_MOVE:
				currentIndex = getTouchedIndex(x,y);
				break;
			case MotionEvent.ACTION_UP:
				currentIndex = getTouchedIndex(x,y);
				if (currentIndex == touchIndex &&
								currentIndex!=-1) {
					//invoke callback
				}
				touchIndex = currentIndex = -1;
				break;
			case MotionEvent.ACTION_CANCEL:
				touchIndex = currentIndex = -1;
				break;
		}

		invalidate();
		return true;
	}

	private int getTouchedIndex(int x, int y) {
		if (center_region.contains(x, y)) {
			return INDEX_CENTER;
		} else if (left_region.contains(x,y)){
			return INDEX_LEFT;
		} else if (up_region.contains(x,y)) {
			return INDEX_UP;
		} else if (right_region.contains(x,y)) {
			return INDEX_RIGHT;
		} else if (down_region.contains(x,y)){
			return INDEX_DOWN;
		} else {
			return -1;
		}
	}
}
