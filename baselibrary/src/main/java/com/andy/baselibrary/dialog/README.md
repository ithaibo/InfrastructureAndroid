DialogFragment窗口大小问题

#问题描述#
Dialog的布局文件：
``` xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</LinearLayout>
```

以上代码表明，TextView宽度应该是占满整个屏幕，然而却出现了LinearLayout、TextView两个的宽度都仅仅是TextView中文本的占用的宽度。

##解决办法1##
在DialogFragment中对Dialog的Window进行参数设置：
``` Java
Dialog dialog = getDialog();
if (dialog != null) {
    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
}
```

其效果是TextView与LinearLayout的宽度一致，并且基本充满全屏（有一定的边距）

##解决办法2##
在解决办法1的基础上对Dialog进行背景设置：
``` Java
...
dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //窗口背景透明，没有padding
...
```

效果：充满全屏，边距消除。