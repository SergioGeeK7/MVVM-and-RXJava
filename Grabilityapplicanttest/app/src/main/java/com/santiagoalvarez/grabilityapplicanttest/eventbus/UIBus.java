package com.santiagoalvarez.grabilityapplicanttest.eventbus;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.squareup.otto.Bus;

/**
 * Created by santiagoalvarezmonsalve on 3/19/16.
 */
public class UIBus extends Bus {

    private static final String TAG = UIBus.class.getSimpleName();
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public void postOnUIThread(final Object event, Activity activity) {
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    post(event);
                }
            });
        } else {
            Log.e(TAG, "Attempted to post event on UI thread with null activity!");
        }
    }

    public void postOnNonUIThread(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            post(event);
        } else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    post(event);
                }
            });
        }
    }

    public void postOnUIThreadDelayed(final Object event, int delay) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                post(event);
            }
        }, delay);
    }
}
