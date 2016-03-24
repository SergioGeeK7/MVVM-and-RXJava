package com.santiagoalvarez.grabilityapplicanttest.util;

import android.content.Context;

import com.santiagoalvarez.grabilityapplicanttest.model.Data;
import com.santiagoalvarez.grabilityapplicanttest.rest.RestClient;
import com.santiagoalvarez.grabilityapplicanttest.singleton.DataSingleton;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by santiagoalvarezmonsalve on 3/24/16.
 */
public class CacheStrategy {

    public enum Strategy {
        MEMORY, DISK, NETWORK
    }

    private final Context context;
    private final Strategy firstOneOption;
    private final Strategy secondOneOption;
    private final Strategy lastOneOption;

    public static class CacheStrategyBuilder {

        private final Context context;
        private Strategy firstOneOption;
        private Strategy secondOneOption;
        private Strategy lastOneOption;

        public CacheStrategyBuilder(Context context) {
            this.context = context;
        }

        public CacheStrategyBuilder preferFresh() {
            this.firstOneOption = Strategy.NETWORK;
            this.secondOneOption = Strategy.DISK;
            this.lastOneOption = Strategy.MEMORY;
            return this;
        }

        public CacheStrategyBuilder preferDefault() {
            this.firstOneOption = Strategy.MEMORY;
            this.secondOneOption = Strategy.DISK;
            this.lastOneOption = Strategy.NETWORK;
            return this;
        }

        public CacheStrategy build() {
            return new CacheStrategy(this);
        }
    }

    private CacheStrategy(CacheStrategyBuilder builder) {
        context = builder.context;
        firstOneOption = builder.firstOneOption;
        secondOneOption = builder.secondOneOption;
        lastOneOption = builder.lastOneOption;
    }

    public Observable<Data> getStrategy() {
        return Observable
                .concat(getObservable(firstOneOption), getObservable(secondOneOption), getObservable(lastOneOption))
                .first(RxJavaUtil.isNotNull)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io());
    }

    private Observable<Data> getObservable(Strategy strategy) {
        switch (strategy) {
            case MEMORY:
                return getMemoryObservable();
            case DISK:
                return getDiskObservable();
            case NETWORK:
                return getNetworkObservable();
            default:
                return getNetworkObservable();
        }
    }

    private Observable<Data> getMemoryObservable() {
        return DataSingleton.getData().compose(RxJavaUtil.logSource(Strategy.MEMORY.name()));
    }

    private Observable<Data> getDiskObservable() {
        return FileClient.getData(context).compose(RxJavaUtil.logSource(Strategy.DISK.name()))
                .doOnNext(RxJavaUtil.cacheOnMemory());
    }

    private Observable<Data> getNetworkObservable() {
        return RestClient.getInstance().getPublicService().feed().compose(RxJavaUtil.logSource(Strategy.NETWORK.name()))
                .doOnNext(RxJavaUtil.saveToDiskAndCacheOnMemory(context));
    }
}
