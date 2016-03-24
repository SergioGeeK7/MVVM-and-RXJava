package com.santiagoalvarez.grabilityapplicanttest.singleton;

import com.santiagoalvarez.grabilityapplicanttest.model.Data;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by santiagoalvarezmonsalve on 3/24/16.
 */
public class DataSingleton {

    private static Data dataInstance = null;

    private DataSingleton() {
    }

    public static void updateInstance(Data instance) {
        if (instance != null)
            dataInstance = instance;
    }

    public static Observable<Data> getData() {
        return Observable.create(new Observable.OnSubscribe<Data>() {
            @Override
            public void call(Subscriber<? super Data> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(dataInstance);
                    subscriber.onCompleted();
                }
            }
        });
    }
}
