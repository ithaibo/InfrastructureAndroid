package com.ithaibo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ithaibo.module_view.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andy on 2017/10/23.
 */

public class LevelView extends View {
	private final String TAG = "LevelView";

	private Paint paint;
	private float radiusPoint;  //圆点半径
	private int sides; //多边形边数
	private int scaleLevels; //同心多边形个数
	private int netColor; //蜘蛛网线条颜色
	private int netStrokeWidth; //蜘蛛网线条宽度
	private int colorPoint;

	private float maxRadius;  //最外层顶点到圆心的距离
	private List<Point> pointList;
	private final int DEFAULT_COLOR_POINT = 0xaa55ff55;
	private final int DEFAULT_COLOR_LINE = 0xff252525;
	private final int DEFAULT_RADIUS_POINT = 10;
	private final int DEFAULT_WIDTH = 200;
	private final int DEFAULT_HEIGHT = 200;

	private List<Double> maxValues = new ArrayList<>();
	private List<Double> realValues = new ArrayList<>();

	public LevelView(Context context) {
		super(context);
		init(context, null);
	}

	public LevelView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public LevelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {
		//关闭硬件加速
		setLayerType(View.LAYER_TYPE_SOFTWARE, null);

		if (attrs != null) {
			TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LevelView);
			colorPoint = typedArray.getColor(R.styleable.LevelView_colorPoint, DEFAULT_COLOR_POINT);
			netColor = typedArray.getColor(R.styleable.LevelView_lineColor, DEFAULT_COLOR_LINE);
			radiusPoint = typedArray.getDimensionPixelSize(R.styleable.LevelView_radiusPoint, DEFAULT_RADIUS_POINT);
			sides = typedArray.getInteger(R.styleable.LevelView_sides, 6);
			netStrokeWidth = typedArray.getDimensionPixelSize(R.styleable.LevelView_netStrokeWidth, 2);
			scaleLevels = typedArray.getInteger(R.styleable.LevelView_scaleLevels, 5);
		} else {
			colorPoint = DEFAULT_COLOR_POINT;
			netColor = DEFAULT_COLOR_LINE;
			radiusPoint = DEFAULT_RADIUS_POINT;
			sides = 6;
			netStrokeWidth = 2;
			scaleLevels = 5;
		}
		//初始化paint
		paint = new Paint();
		paint.setAntiAlias(true);

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		//处理wrap_content
		int mWidth = widthMode == MeasureSpec.AT_MOST ? DEFAULT_WIDTH : widthSize;
		int mHeight = heightMode == MeasureSpec.AT_MOST ? DEFAULT_HEIGHT : heightSize;

		setMeasuredDimension(mWidth, mHeight);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		maxRadius = Math.min(w / 2, h / 2) - 10;
		calculateNetPoints();
	}

	private void calculateNetPoints() {
		pointList = new LinkedList<>();
		if (sides <= 0) {
			return;
		}
		double itemAngle = 2 * Math.PI / sides;
		for (int i = 0; i < this.sides; i++) {
			pointList.add(new Point(maxRadius, i * itemAngle));
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Log.i(TAG, "maxRadius: " + maxRadius);
		//坐标移至中心
		canvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);

		//画出蜘蛛网
		Path path = new Path();
		addSides2Path(path);
		addDiagonals2Path(path);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(netStrokeWidth);
		paint.setColor(netColor);
		canvas.drawPath(path, paint);

		//画出值域部分
		drawCovered(canvas);
	}

	private void drawCovered(Canvas canvas) {
		Path path = new Path();
		if (realValues == null || realValues.size() <= 0 ||
						maxValues == null || maxValues.size() <= 0 ||
						maxValues.size() != realValues.size()) {
			Log.w(TAG, "value is not available, cannot draw the covered.");
			return;
		}
		for (int i = 0; i < pointList.size(); i++) {
			float[] xy = calculateXY(maxRadius * realValues.get(i) / maxValues.get(i), pointList.get(i).angle);
			if (i == 0) {
				path.moveTo(xy[0], xy[1]);
			} else {
				path.lineTo(xy[0], xy[1]);
			}
		}
		path.close();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(0x8833ff00);
		canvas.drawPath(path, paint);
		//draw points
		drawPoints(canvas);
	}

	private void drawPoints(Canvas canvas) {
		paint.setColor(colorPoint);
		paint.setStyle(Paint.Style.FILL);
		for (int i = 0; i < pointList.size(); i++) {
			float[] xy = calculateXY(maxRadius * realValues.get(i) / maxValues.get(i), pointList.get(i).angle);
			canvas.drawCircle(xy[0], xy[1], radiusPoint, paint);
		}
	}

	private void addSides2Path(Path path) {
		for (int i = scaleLevels; i > 0; i--) {
			int tmpR = (int) (maxRadius * i / scaleLevels);
			for (int j = 0; j < sides; j++) {
				float[] xy = calculateXY(tmpR, pointList.get(j).angle);
				if (j == 0) {
					//set point start
					path.moveTo(xy[0], xy[1]);
				} else {
					//draw side
					path.lineTo(xy[0], xy[1]);
				}
			}
			//close path
			path.close();
		}
	}

	private void addDiagonals2Path(Path path) {
		if (path == null)
			return;
		for (int i = 0; i < sides; i++) {
			path.moveTo(0, 0);
			path.lineTo(pointList.get(i).x, pointList.get(i).y);
		}
	}

	private class Point {
		int x;
		int y;
		private double r;
		private double angle; //radian

		public Point(double r, double angle) {
			this.r = r;
			this.angle = angle;
			calculateXY(r);
		}

		public void setR(double r) {
			this.r = r;
			calculateXY(r);
		}

		private void calculateXY(double r) {
			this.x = (int) (r * Math.cos(angle));
			this.y = (int) (r * Math.sin(angle));
		}

	}

	private float[] calculateXY(double r, double angle) {
		float[] xy = new float[2];
		xy[0] = (float) (r * Math.cos(angle));
		xy[1] = (float) (r * Math.sin(angle));
		return xy;
	}

	public float getRadiusPoint() {
		return radiusPoint;
	}

	public void setRadiusPoint(float radiusPoint) {
		this.radiusPoint = radiusPoint;
		invalidate();
	}

	public int getSides() {
		return sides;
	}

	public void setSides(int sides) {
		this.sides = sides;
		invalidate();
	}

	public int getScaleLevels() {
		return scaleLevels;
	}

	public void setScaleLevels(int scaleLevels) {
		this.scaleLevels = scaleLevels;
		invalidate();
	}

	public float getMaxRadius() {
		return maxRadius;
	}

	public void setMaxRadius(float maxRadius) {
		this.maxRadius = maxRadius;
		invalidate();
	}

	public int getNetColor() {
		return netColor;
	}

	public void setNetColor(int netColor) {
		this.netColor = netColor;
		invalidate();
	}

	public int getNetStrokeWidth() {
		return netStrokeWidth;
	}

	public void setNetStrokeWidth(int netStrokeWidth) {
		this.netStrokeWidth = netStrokeWidth;
		invalidate();
	}

	public List<Double> getMaxValues() {
		return maxValues;
	}

	public void setMaxValues(List<Double> maxValues) {
		this.maxValues = maxValues;
	}

	public void setMaxValues(Double maxValue) {
		this.maxValues = new ArrayList<>();
		for (int i = 0; i < sides; i++) {
			maxValues.add(maxValue);
		}
		invalidate();
	}

	public List<Double> getRealValues() {
		return realValues;
	}

	public void setRealValues(List<Double> realValues) {
		this.realValues = realValues;
	}
}
