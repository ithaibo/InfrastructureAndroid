# GreenDao
GreenDao是Android开发中常用的ROM工具。本文主要介绍GreenDao3的集成。

## 版本
本文写作时间2017-05-16，GreenDao的最新版本为3.2.0.

## 集成步骤
 - 在根目录下编辑build.gradle, 添加mavenCentral和greendao-gradle-plugin：
```groovy
buildscript {
    repositories {
        jcenter()
        mavenCentral() // add repository
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.2.2'
        classpath 'org.greenrobot:greendao-gradle-plugin:3.2.2' // add plugin
    }
}
```

- 修改app模块下的build.gradle， 添加plugin和greendao的依赖：
 ```groovy
 apply plugin: 'org.greenrobot.greendao' // apply plugin
 compile 'org.greenrobot:greendao:3.2.0' //add dependency

 ```

OK, 基本的集成已经完成了，这个时候执行make project还是不能生成DaoMaster等类。
因为GreenDao3建议使用注解定义scheme。

## add Entity

```Java
@Entity
public class User {
    @Id
    private Long id;
 
    private String name;
 
    @Transient
    private int tempUsageCount; // not persisted
 
   // getters and setters for id and user ...
}
```
现在，执行make project，将会看到在build/generated/source看到greendao的目录。这里面有
DaoMaster, DaoSession, UserDao。
