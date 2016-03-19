package com.santiagoalvarez.grabilityapplicanttest.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.santiagoalvarez.grabilityapplicanttest.R;
import com.santiagoalvarez.grabilityapplicanttest.model.Feed;
import com.santiagoalvarez.grabilityapplicanttest.rest.RestClient;

import rx.Observer;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private static final String TAG = "MainActivityFragment";

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        v.findViewById(R.id.bTestRequest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestClient.getInstance().getPublicService()
                        .feed()
                        .subscribe(new Observer<Feed>() {
                            @Override
                            public void onCompleted() {
                                Log.d(TAG, "onCompleted: ");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "onError: " + e.getMessage());
                            }

                            @Override
                            public void onNext(Feed feed) {
                                if (feed != null) {
                                    Log.d(TAG, "onNext: ");
                                }
                            }
                        });
            }
        });
        return v;
    }
}
