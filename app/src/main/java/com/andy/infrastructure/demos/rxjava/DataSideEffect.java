package com.andy.infrastructure.demos.rxjava;


import com.andy.baselibrary.utils.LogUtil;

import rx.Observable;
import rx.functions.Action0;

/**
 * Created by Smily on 2017/1/1.
 */

public class DataSideEffect {
    public void doOnCompleted(){
        Observable ob = Observable.just("A", "B", "C", "D")
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        LogUtil.d("Smily");
                    }
                });
    }
}
