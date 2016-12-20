# rxjava
这个包中是关于对RxJava的一些实践Demo

##版本介绍
    io.reactivex:rxjava:1.2.4
    io.reactivex:rxandroid:1.2.1
    com.squareup.retrofit2:retrofit-converters:2.1.0
    com.squareup.retrofit2:converter-gson:2.1.0

##学习小结
    1. 在使用@BindView(R.id.tvRes) TextView tvRes 时，需要注意的是:对象tvRes不能是private或static，且名字必须和tvRes一致。
