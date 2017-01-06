#Data Binding
根据变量，自动赋值到各widget
##What
用一个变量绑定各个widget的值。
##Why
在Java文件中，只更改一个对象，而无须手动设置某个widget（TextView）的值。
##How
>1.编写layout文件，这里的layout为：
    act\_data\_bind\_demo.xml
>    
  这里需要先准备变量
    <data>
       <variable
          name="user"
          type="com.andy.infrastructure.bean.Customer" />
    </data>

>  在具体的widget上使用该变量
	<TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@{user.name}"/>

----
>2.Binding data
上面的步骤完成后，需要先编译整个Project，因为在这个过程中会生成一些类：DataBindingUtil，ActDataBindDemoBinding

---
**注意**：这里的ActDataBindDemoBinding是根据上面的布局文件的名字生成的。

在onCreate方法中：

	ActDataBindDemoBinding binding = DataBindingUtil.setContentView(this, 
             R.layout.act_data_bind_demo);

	customer = new Customer();
	customer.setName("Andy");
	customer.setMobile("13866668888");

	binding.setUser(customer);


运行后，可以看到页面中的两个TextView都有值。


###问题：如何获取EditText的输入内容？

**failed：**

    <EditText
	   android:layout_width="match_parent"
	   android:layout_height="wrap_content"
	   android:inputType="textEmailAddress"
	   android:text="@{user.email}"/>


**It's work：** "

    ...android:text="@={user.email}"

###问题：如何通过一个按钮改变当前页面中的TextView的值？
1.module类继承BaseObservable

    public class Customer extends BaseObservable {
    ...
    public void setMobile(String mobile) {
        this.mobile = mobile;
        notifyPropertyChanged(BR.mobile);
    }
继承BaseObservable，同时在setter方法中调用notifyPropertyChanged。

2.更新Java中的Customer对象，例如当mobile改变，UI会制动刷新。

###Custom Binding Class Names
默认情况下，Binding Class是根据layout文件名生成的。（例如，当layout文件为act\_data\_bind\_demo.xml，生成的class为：ActDataBindDemoBinding）

可以自定义类名，以及设定包名：

    <data class="com.andy.infrastructure.demos.databinding.DataBind">
        <variable ...
    </data>

上面的代码中，会在包com.andy.infrastructure.demos.databinding下生成一个名为DataBind的类。**注意**这个package必须是存在的，当然也可以不指定package（会在默认的包下生成该类）。

###Include
在layout文件中使用include时，也可以传递数据到指定的layout中。需要额外2个步骤：

1. 确保传递的变量在两个layout中都有声明

    <layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:bind="http://schemas.android.com/apk/res-auto">
    <data class="com.andy.infrastructure.demos.databinding.DataBind">
        <variable
            name="user"
            type="com.andy.infrastructure.demos.databinding.Customer" />
        ...
    </data>
    ...
        <include layout="@layout/user_name"
            bind:user="@{user}"/>

    被引入的layout：
    ...
    <data>
        <variable
            name="user"
            type="com.andy.infrastructure.demos.databinding.Customer" />
    </data>

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="15dp"
        android:text="@{user.name}">

2. 外层layout中传递变量

    <include layout="@layout/user_name"
            bind:user="@{user}"/>

###Expression Language
 

- 数学运算 + - / * % <br>
- 字符串拼接 + <br>
- 逻辑运算符 && ||  <br>
- 位运算 & | ^ <br>
- Unary + - ! ~  <br>
- 位移 >> >>> << <br>
- 比较运算符 == > < >= <= <br>
- instanceof <br>
- Grouping () <br>
- Literals character, String, numeric, null <br>
- Cast <br>
- Method calls <br>
- Field access <br>
- Array access [] <br>
- 三元运算符 ? : <br>

- Null Coalescing Operator ??
> 如果??左边不为空就使用左边值，否则就使用右边的值。

     android:text="@{user.displayName ?? user.lastName}"

上面的代码相当于：

    android:text="@{user.displayName != null ? user.displayName : user.lastName}"

###Data Objects
三种数据更新提示机制：
>  - Observable Object
>  - Observable Fields
>  - Observable Collections

####Observable Object
这种方式，需要做三个步骤：
>  - 继承BaseObservable 
>  - 为getter添加@Bindable
>  - 在setter中添加代码notifyPropertyChanged(BR.name);

###Views With IDs
> A public final field will be generated for each View with an ID in the layout. The binding does a single pass on the View hierarchy, extracting the Views with IDs. This mechanism can be faster than calling findViewById for several Views.

