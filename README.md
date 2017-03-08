# InfrastructureAndroid
这是一个Android APP快速开发框架

##代码结构
baselibrary是一个与业务逻辑无关的基础框架，其中包含了BaseActivity BaseFragment holder utils adapter的封装

##使用的第三方库
    compile 'com.squareup.okhttp3:okhttp:3.5.0'
    compile 'com.github.bumptech.glide:glide:3.7.0'
    compile 'com.google.code.gson:gson:2.8.0'
    compile 'com.jakewharton:butterknife:8.4.0'
    compile 'io.reactivex.rxjava2:rxjava:2.0.2'
    compile 'com.squareup.retrofit2:retrofit:2.1.0'

##baselibrary基本用法介绍


# shortcuts.xml（res/xml）
这里存放的是一些shortcut（Android N最新功能，类似于IOS的3D Touch，在Launcher长按可弹出一些快捷方式）

##simpleStaticShortcut
    这是一个简单的静态Shortcut，可进入SimpleRetrofit。
###实现方式：
    1. 在res/xml目录下创建一个shortcut.xml文件；
    2. 在该文件中编写Shortcut，例如：
       <shortcut
            android:shortcutId="simpleStaticShortcut"
            android:enabled="true"
            android:icon="@mipmap/ic_launcher"                                              //这里指定显示的logo
            android:shortcutShortLabel="@string/compose_shortcut_short_label1"              //创建Shortcut快捷方式时显示的label
            android:shortcutLongLabel="@string/compose_shortcut_long_label1"                //长按应用后，显示的label
            android:shortcutDisabledMessage="@string/compose_disabled_message1">            //不详
            <intent
                android:action="android.intent.action.VIEW"
                android:targetPackage="com.andy.infrastructure"                             //包名
                android:targetClass="com.andy.infrastructure.rxjava.DemoRxJavaActivity" />  //需要打开的Activity
            <categories android:name="android.shortcut.conversation" />
        </shortcut>
    /**
     * @Author: AndyWu
     * @Date: 2016-12-23
     */

##Demo
该project的demo有：

- databind              DataBinding示例
- material              
- multiprocess          多进程示例
- retrofit              Retrofit示例
- rxjava                RxJava示例
- mvp                   RxJava + Retrofit +DataBinding

相关示例可以查看源代码，及其说明。

## 更新说明 ##
| time | description |
| ------- | ------- |
| 1/6/2017 4:52:48 PM | null |
| 3/8/2017 11:29:21 AM  | mvp |
