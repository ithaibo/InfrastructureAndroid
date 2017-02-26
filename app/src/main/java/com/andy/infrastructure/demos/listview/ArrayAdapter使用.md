# ArrayAdapter #

ArrayAdapter的Constructor：
 - public ArrayAdapter(@NonNull Context context, @LayoutRes int resource)
 - public ArrayAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId)
 - public ArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull T[] objects)
 - public ArrayAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull T[] objects)
 - public ArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<T> objects)

 相关参数说明：

 - resource: The resource ID for a layout file **containing a TextView** to use when instantiating views.
 - textViewResourceId
 - T[] objects: The objects, instance of T[], to represent in the ListView.
 - List<T> objects: The objects, instance of List, to represent in the ListView.

 resource官方文档说明：
> The resource ID for a layout file **containing a TextView** to use when instantiating views.

上面说的是一个包含TextView的layout，这个说法是有点问题的，到底是只有一个TextView节点的Laayout文件,如下所示：

``` XML
<TextView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:paddingTop="10dp"
    android:paddingBottom="10dp"
    android:textSize="15sp"
    android:textColor="#252525"
    android:gravity="center"
    tools:text="stub"/>
```
还是可以如下这样：

``` XML
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:paddingTop="10dp"
        android:paddingBottom="10dp"
        android:textSize="15sp"
        android:textColor="#252525"
        android:gravity="center"
        tools:text="stub"/>

</LinearLayout>
```
针对上面的疑问做一个这样一个尝试：使用的constructor为：ArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<T> objects)，
其中resource的值为：上面第二种。其结果如下：

报错了，日志如下：
```
java.lang.IllegalStateException: ArrayAdapter requires the resource ID to be a TextView
```

因此得出结论：构造器中传入的resource必须是一个layout，这个layout有且仅有一个TextView，没有其他任何父节点、兄弟节点。

