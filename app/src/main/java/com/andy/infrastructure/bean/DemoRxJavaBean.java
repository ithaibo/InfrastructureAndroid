package com.andy.infrastructure.bean;

import rx.Observable;
import rx.Observer;

/**
 * Created by Andy on 2016/12/28.
 */

public class DemoRxJavaBean extends Bean {
    private String demoTitle;
    private String methodName;
    private Observable observable;
    private Observer observer;

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
