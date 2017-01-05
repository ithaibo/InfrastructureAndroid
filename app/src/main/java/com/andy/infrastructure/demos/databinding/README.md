#Data Bindin
根据变量，自动赋值到各widget
##What
用一个变量绑定各个widget的值。
##Why
在Java文件中，只更改一个对象，而无须手动设置某个widget（TextView）的值。
##How
1.编写layout文件，这里的layout为：act_data_bind_demo
  这里需要先准备变量
    <data>
        <variable
            name="user"
            type="com.andy.infrastructure.bean.Customer" />
    </data>

  在具体的widget上使用该变量
	<TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@{user.name}"/>
2.Binding data
上面的步骤完成后，需要先编译整个Project，因为在这个过程中会生成一些类：DataBindingUtil，ActDataBindDemoBinding

###注意：这里的ActDataBindDemoBinding是根据上面的布局文件的名字生成的。

在onCreate方法中：
	ActDataBindDemoBinding binding = DataBindingUtil.setContentView(this, R.layout.act_data_bind_demo);
	customer = new Customer();
	customer.setName("Andy");
	customer.setMobile("13866668888");
	binding.setUser(customer);
运行后，可以看到页面中的两个TextView都有值。

###数据绑定，只是在layout中，会自动根据变量user去给name、mobile赋值，如果user改变，TextView也会刷新。但是，如果user的更新，在Java中，需要调用binding.setUser。