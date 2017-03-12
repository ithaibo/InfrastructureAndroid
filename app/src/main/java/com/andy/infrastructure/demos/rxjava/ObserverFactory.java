package com.andy.infrastructure.demos.rxjava;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import rx.Observer;
import rx.functions.Action0;

/**
 * Created by andy on 17-3-12.
 */

public class ObserverFactory {
    private static final String TAG = "ObserverFactory";

    /**
     * just print log.
     * @param context
     * @return
     */
    public static Observer<String> obsLogcat(final Context context) {
        return new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "logcat oberver, onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "logcat oberver, onError");
            }

            @Override
            public void onNext(String o) {
                Log.i(TAG, "observer factory, " + "logcat.onNext data : " + o);
            }
        };
    }

    public static Observer<Drawable> drawableObserver(final ImageView iv) {
        return new Observer<Drawable>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "drawable load completed");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "drawable load with error!");
            }

            @Override
            public void onNext(Drawable drawable) {
                iv.setImageDrawable(drawable);
            }
        };
    }
}
