package com.santiagoalvarez.grabilityapplicanttest.util;

import android.content.Context;

import com.santiagoalvarez.grabilityapplicanttest.model.Feed;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FileClient {

    private FileClient() {
        throw new UnsupportedOperationException("utility class. Please don't instantiate this class");
    }

    public static Observable<Feed> saveFeed(final Context context, final Feed order) {
        return Observable
                .create(new Observable.OnSubscribe<Feed>() {
                    @Override
                    public void call(Subscriber<? super Feed> subscriber) {
                        if (!subscriber.isUnsubscribed()) {
                            try {
                                FileUtils.saveObjectToDisk(context, Feed.class.getName(), order);
                                subscriber.onNext(null);
                                subscriber.onCompleted();
                            } catch (IOException e) {
                                subscriber.onError(e);
                                e.printStackTrace();
                            }
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Feed> getFed(final Context context) {
        return Observable
                .create(new Observable.OnSubscribe<Feed>() {
                    @Override
                    public void call(Subscriber<? super Feed> subscriber) {
                        if (!subscriber.isUnsubscribed()) {
                            try {
                                subscriber.onNext((Feed) FileUtils.getObjectFromDisk(context, Feed.class.getName()));
                                subscriber.onCompleted();
                            } catch (IOException | ClassNotFoundException e) {
                                subscriber.onError(e);
                                e.printStackTrace();
                            }
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<Void> deleteFeed(final Context context) {
        return Observable
                .create(new Observable.OnSubscribe<Void>() {
                    @Override
                    public void call(Subscriber<? super Void> subscriber) {
                        if (!subscriber.isUnsubscribed()) {
                            try {
                                FileUtils.deleteObjectToDisk(context, Feed.class.getName());
                                subscriber.onNext(null);
                                subscriber.onCompleted();
                            } catch (IOException e) {
                                subscriber.onError(e);
                                e.printStackTrace();
                            }
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
