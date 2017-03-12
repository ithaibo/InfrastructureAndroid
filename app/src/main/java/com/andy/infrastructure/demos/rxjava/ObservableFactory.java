package com.andy.infrastructure.demos.rxjava;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.repacked.google.common.util.concurrent.AbstractScheduledService;
import android.graphics.drawable.Drawable;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by andy on 17-3-12.
 */

public class ObservableFactory {

    public static Observable<Integer> rangeIntObservable(int start, int end) {
        return Observable.range(start, end);
    }

    /**
     * Observable to get drawable, in other thread.
     * @param id
     * @param context
     * @return
     */
    public static Observable<Drawable> drawableObservable(final int id, final Context context) {
        return Observable.create(new Observable.OnSubscribe<Drawable>(){

            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = context.getResources().getDrawable(id);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread());
    }
}
