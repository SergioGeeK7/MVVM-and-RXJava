package com.santiagoalvarez.grabilityapplicanttest.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.santiagoalvarez.grabilityapplicanttest.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private static final String TAG = "MainActivityFragment";


//    @Bind(R.id.rVHome)
//    RecyclerView rVHome;
//    @Bind(R.id.sRHome)
//    SwipeRefreshLayout sRHome;
//    @Bind(R.id.rLFrgHome)
//    RelativeLayout rLFrgHome;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
//        sRHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                initData();
//            }
//        });
//        v.findViewById(R.id.bTestRequest).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                RestClient.getInstance().getPublicService()
//                        .feed()
//                        .subscribe(new Observer<Data>() {
//                            @Override
//                            public void onCompleted() {
//                                Log.d(TAG, "onCompleted: ");
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                Log.d(TAG, "onError: " + e.getMessage());
//                            }
//
//                            @Override
//                            public void onNext(Data data) {
//                                if (data != null) {
//                                    Log.d(TAG, "onNext: ");
//                                }
//                            }
//                        });
//            }
//        });
        return v;
    }
}
