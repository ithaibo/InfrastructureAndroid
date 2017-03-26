View的工作过程包含三个主要部分：measure, layout, draw。其中measure是完成View的大小测量，为后面的过程做准备。本位将主要介绍android中View的测量过程。
View树的测量工作从ViewRootImpl.performTransform()开始(Talk is cheap, show me the code)：
``` Java
private void performTraversals() {
   ...
   performMeasure(childWidthMeasureSpec, childHeightMeasureSpec); //measure start
   ...
   if (didLayout) {
       performLayout(lp, desiredWindowWidth, desiredWindowHeight);//layout start
   }
   if (!cancelDraw && !newSurface) {
       if (!skipDraw || mReportNextDraw) {
           ...
           performDraw();                                         //draw start
       }
   }
}
```

``` Java
    private void performMeasure(int childWidthMeasureSpec, int childHeightMeasureSpec) {
        Trace.traceBegin(Trace.TRACE_TAG_VIEW, "measure");
        try {
            mView.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        } finally {
            Trace.traceEnd(Trace.TRACE_TAG_VIEW);
        }
    }
```

android中每个界面(Activity, Fragment, Dialog...)都有一个Window，在Window中绘制DevorView(DecorView继承于FrameLayout，其部分源码如下)，DecorView中再绘制界面中用户设置的layout。
``` Java
    private final class DecorView extends FrameLayout implements RootViewSurfaceTaker {
        ...
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            ...
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            ...
        }
        ...
    }
```
从上面的代码中可以看出，DecorView是一个extends FrameLayout的ViewGroup, 因此在measure方法中调用onMeasure方法，其源码如下：
``` Java
android.widget.FrameLayout.onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
...
 for (int i = 0; i < count; i++) {
    final View child = getChildAt(i);
    if (mMeasureAllChildren || child.getVisibility() != GONE) {
       measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
       ...
    }
 }
```
继续看measureChildWithMargins:
``` Java
android.view.ViewGroup.measureChildWithMargins(View child,
               int parentWidthMeasureSpec, int widthUsed,
               int parentHeightMeasureSpec, int heightUsed) {
...
child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
}
```

上面的方法中调用了子View的measure方法，measure方法会继续调用子View的onMeasure方法：
``` Java
public final void measure(int widthMeasureSpec, int heightMeasureSpec) {
...
   onMeasure(widthMeasureSpec, heightMeasureSpec);
...
}
```
以上就是Activity到View的测量基本过程。