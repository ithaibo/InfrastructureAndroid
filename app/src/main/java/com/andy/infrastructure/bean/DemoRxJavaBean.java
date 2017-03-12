package com.andy.infrastructure.bean;

import com.andy.baselibrary.utils.LogUtil;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

/**
 * Created by Andy on 2016/12/28.
 */

public class DemoRxJavaBean<T> extends Bean {
    private String demoTitle;
    private String methodName;
    private Observable observable;
    private Observer<T> observer = new Observer<T>() {
        @Override
        public void onCompleted() {
            LogUtil.d("complete");
        }

        @Override
        public void onError(Throwable e) {
            LogUtil.d("onError: " +e);
        }

        @Override
        public void onNext(T v) {
            LogUtil.d("onNext: " + v);
        }
    };

    public String getDemoTitle() {
        return demoTitle;
    }

    public DemoRxJavaBean setDemoTitle(String demoTitle) {
        this.demoTitle = demoTitle;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public DemoRxJavaBean setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public Observable getObservable() {
        return observable;
    }

    public Observer getObserver() {
        return observer;
    }

    public DemoRxJavaBean setObservable(Observable observable) {
        this.observable = observable;
        return this;
    }

    public DemoRxJavaBean setObserver(Observer observer) {
        this.observer = observer;
        return this;
    }

    @Override
    public String toString() {
        return "DemoRxJavaBean{" +
                "demoTitle='" + demoTitle + '\'' +
                ", methodName='" + methodName + '\'' +
                ", observable=" + observable +
                ", observer=" + observer +
                '}';
    }
}
