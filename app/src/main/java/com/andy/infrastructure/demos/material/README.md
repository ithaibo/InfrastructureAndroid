# Material Design #

## Components ##
###SnackBar###
#### 实现 ####
 - 没有Action，自动消失
``` Java
Snackbar.make(getView(), R.string.text_show_snackbar_simple, Snackbar.LENGTH_LONG).show();
```
 - 有一个Action
``` Java
Snackbar.make(getView(),R.string.text_show_snackbar_action,Snackbar.LENGTH_LONG)
        .setAction(R.string.label_text_action_snack,new View.OnClickListener(){
    @Override
    public void onClick(View v){
        showToast("你点击了SnackBar的Action按钮");
        }
    })
    .setActionTextColor(getContext().getResources().getColor(R.color.colorAccent))
    .show();
```

###CoordinatorLayout###
> 参考[CoordinatorLayout与滚动的处理]("http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0717/3196.html")
#### 作用 ####
 - 让浮动操作按钮上下滑动，为SnackBar留出空间
 - 扩展或缩小ToolBar或头部，让主内容区域有更多的空间
 - 控制哪个View应该扩展还是收缩，以及其显示大小比例，包括视察滚动效果动画
#### 实现 ####

### TextInputLayout ###
####作用####
 - 为EditText设置label
 - 为EditText添加警告提示信息
#### 实现 ####
