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

查看ArrayAdapter源码：
``` Jva
public @NonNull View getView(int position, @Nullable View convertView,
            @NonNull ViewGroup parent) {
        return createViewFromResource(mInflater, position, convertView, parent, mResource);
    }

    private @NonNull View createViewFromResource(@NonNull LayoutInflater inflater, int position,
            @Nullable View convertView, @NonNull ViewGroup parent, int resource) {
        final View view;
        final TextView text;
        ...
        try {
            if (mFieldId == 0) {
                //  If no custom field is assigned, assume the whole resource is a TextView
                text = (TextView) view;
            }
        return view;
```
上面的源码中，如何mFiledId（TextView的Id）如果为零,直接将view（整个layout）转为TextView。因此，必须使用只有一个节点:TextView的布局。
