package com.andy.infrastructure.demos.mvp.presenter;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Andy on 2017/3/7.
 */

public class BasePresenterImpl implements Presenter {
    private CompositeSubscription compositeSubscription;

    protected void addSubscription(Subscription subscription) {
        if (this.compositeSubscription == null) {
            this.compositeSubscription = new CompositeSubscription();
        }
        this.compositeSubscription.add(subscription);
    }

    @Override
    public void unsubscribe() {
        if (this.compositeSubscription !=null ) {
            this.compositeSubscription.unsubscribe();
        }
    }
}
