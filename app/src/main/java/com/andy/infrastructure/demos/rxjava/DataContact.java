package com.andy.infrastructure.demos.rxjava;


import android.content.Context;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

/**
 * Created by Smily on 2017/1/2.
 */

public class DataContact {
    private Context context;
    Observable<String> w1 = Observable.just(
            "First",
            "Second",
            "Third"
    );

    Observable<String> w2 = Observable.just(
            "Fourth",
            "Fifth"
    );

    public DataContact(Context context) {
        this.context = context;
    }

    public Observable getConcatObservable() {
        return Observable.concat(w1, w2);
    }

    public Observer<String> getObserver() {
        return new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            }
        };
    }

    public Observable getGroupConcatObservable() {
        Observable ob = Observable.
                concat(Observable.concat(w1, w2)
                        .groupBy(new Func1<String, Character>() {
                            /**根据首字母分组**/

                            @Override
                            public Character call(String s) {
                                return s.charAt(0);
                            }
                        }));
        return ob;
    }

    public Observable getMergeObservable() {
        return Observable.merge(
                Observable.interval(100, TimeUnit.MILLISECONDS).map(new Func1<Long, String>() {
                    @Override
                    public String call(Long aLong) {
                        return "First";
                    }
                }),
                Observable.interval(50, TimeUnit.MILLISECONDS).map(new Func1<Long, String>() {
                    @Override
                    public String call(Long aLong) {
                        return "Second";
                    }
                })
        ).take(6);
    }
}
