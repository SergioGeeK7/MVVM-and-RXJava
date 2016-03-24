package com.santiagoalvarez.grabilityapplicanttest.util;

import android.content.Context;
import android.util.Log;

import com.santiagoalvarez.grabilityapplicanttest.model.Data;
import com.santiagoalvarez.grabilityapplicanttest.singleton.DataSingleton;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by santiagoalvarezmonsalve on 3/21/16.
 */
public class RxJavaUtil {

    private static final String TAG = "RxJavaUtil";
    public static final int CACHE_STALE_MINUTES = 5;

    private RxJavaUtil() {
        throw new UnsupportedOperationException("utility class. Please don't instantiate this class");
    }

    /**
     * check nullity
     */
    public static Func1<Object, Boolean> isNotNull = new Func1<Object, Boolean>() {
        @Override
        public Boolean call(Object o) {
            return o != null;
        }
    };

    /**
     * check if given data was updated in the pass 5 minutes
     */
    public static Func1<Data, Boolean> isUpToDate = new Func1<Data, Boolean>() {
        @Override
        public Boolean call(Data data) {
            if (data == null) return false;

            long minutesSinceLastUpdate =
                    new Duration(DateTime.parse(data.getFeed().getUpdated().getLabel())
                            , DateTime.now()).getStandardMinutes();
            return !(minutesSinceLastUpdate > CACHE_STALE_MINUTES);
        }
    };

    public static Action1<Data> cacheOnMemory() {
        return new Action1<Data>() {
            @Override
            public void call(Data data) {
                DataSingleton.updateInstance(data);
            }
        };
    }


    public static Action1<Data> saveToDiskAndCacheOnMemory(final Context context) {
        return new Action1<Data>() {
            @Override
            public void call(Data data) {
                FileClient.saveData(context, data)
                        .subscribe(cacheOnMemory());
            }
        };
    }


    public static Observable.Transformer<Data, Data> logSource(final String source) {
        return new Observable.Transformer<Data, Data>() {
            @Override
            public Observable<Data> call(Observable<Data> dataObservable) {
                return dataObservable
                        .doOnNext(new Action1<Data>() {
                            @Override
                            public void call(Data data) {
                                if (data != null) {
                                    final long minutesSinceLastUpdate =
                                            new Duration(DateTime.parse(data.getFeed().getUpdated().getLabel())
                                                    , DateTime.now()).getStandardMinutes();
                                    if (minutesSinceLastUpdate > CACHE_STALE_MINUTES) {
                                        Log.d(TAG, "logSource: " + source + " has stale data.");
                                    } else {
                                        Log.d(TAG, "logSource: " + source + " has the data i'm looking for!");
                                    }
                                } else {
                                    Log.d(TAG, "logSource: " + source + " does not have any data.");
                                }
                            }
                        });
            }
        };
    }
}


