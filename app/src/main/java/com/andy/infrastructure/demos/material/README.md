# Material Design #

## Components ##
###SnackBar###
尺寸：
 - Mobile
     - Single-line snackbar height: 48dp
     - Multi-line snackbar height: 80dp
     - Text: Roboto Regular 14sp
     - Action button: Roboto Medium 14sp, all-caps text
     - Default background fill: #323232 100%

 - Tablet/desktop
     - Single-line snackbar height: 48dp tall
     - Minimum width: 288dp
     - Maximum width: 568dp
     - 2dp rounded corner
     - Text: Roboto Regular 14sp
     - Action button: Roboto Medium 14sp, all-caps text
     - Default background fill: #323232 100%
     - Alignment: Centered or left-aligned 24dp from the left and bottom edges of the screen

API使用：
 - 没有Action，自动消失
``` Java
Snackbar.make(getView(), R.string.text_show_snackbar_simple, Snackbar.LENGTH_LONG).show();
```

 - 有一个Action
``` Java

```