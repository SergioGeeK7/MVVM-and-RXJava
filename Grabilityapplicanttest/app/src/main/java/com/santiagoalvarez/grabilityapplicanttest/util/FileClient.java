package com.santiagoalvarez.grabilityapplicanttest.util;

import android.content.Context;

import com.santiagoalvarez.grabilityapplicanttest.model.Data;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FileClient {

    private FileClient() {
        throw new UnsupportedOperationException("utility class. Please don't instantiate this class");
    }

    public static Observable<Data> saveData(final Context context, final Data data) {
        return Observable
                .create(new Observable.OnSubscribe<Data>() {
                    @Override
                    public void call(Subscriber<? super Data> subscriber) {
                        if (!subscriber.isUnsubscribed()) {
                            try {
                                FileUtils.saveObjectToDisk(context, Data.class.getName(), data);
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

    public static Observable<Data> getData(final Context context) {
        return Observable
                .create(new Observable.OnSubscribe<Data>() {
                    @Override
                    public void call(Subscriber<? super Data> subscriber) {
                        if (!subscriber.isUnsubscribed()) {
                            try {
                                subscriber.onNext((Data) FileUtils.getObjectFromDisk(context, Data.class.getName()));
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

    public static Observable<Void> deleteData(final Context context) {
        return Observable
                .create(new Observable.OnSubscribe<Void>() {
                    @Override
                    public void call(Subscriber<? super Void> subscriber) {
                        if (!subscriber.isUnsubscribed()) {
                            try {
                                FileUtils.deleteObjectToDisk(context, Data.class.getName());
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
