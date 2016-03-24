package com.santiagoalvarez.grabilityapplicanttest.ui.home;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.santiagoalvarez.grabilityapplicanttest.util.CacheStrategy;
import com.santiagoalvarez.grabilityapplicanttest.R;
import com.santiagoalvarez.grabilityapplicanttest.base.BaseFragment;
import com.santiagoalvarez.grabilityapplicanttest.databinding.FragmentHomeBinding;
import com.santiagoalvarez.grabilityapplicanttest.eventbus.BusClient;
import com.santiagoalvarez.grabilityapplicanttest.eventbus.events.EventSnackbarMessage;
import com.santiagoalvarez.grabilityapplicanttest.model.Data;
import com.santiagoalvarez.grabilityapplicanttest.model.Entry;
import com.santiagoalvarez.grabilityapplicanttest.model.Feed;
import com.santiagoalvarez.grabilityapplicanttest.util.RxJavaUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class HomeFragment extends BaseFragment {

    public static final String TAG = HomeFragment.class.getSimpleName();
    public static final String KEY_CURRENT_PAGER_POSITION = "KEY_CURRENT_PAGER_POSITION";
    public static final String KEY_CURRENT_DATA = "KEY_CURRENT_DATA";

    private int mCurrentPagerPosition = 0;
    private String mSelectedCategory;
    private Data mCurrentData;
    private FragmentHomeBinding mBinding;
    private AdapterHomeCategories mAdapterHomeCategories;
    private Observable<List<String>> mSource;
    private List<String> mList = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_CURRENT_PAGER_POSITION, mCurrentPagerPosition);
        outState.putSerializable(KEY_CURRENT_DATA, mCurrentData);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVars();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initListeners();
        mSource.publish(); /* when view ready populate it*/
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mCurrentPagerPosition = savedInstanceState.getInt(KEY_CURRENT_PAGER_POSITION);
            mBinding.vPHome.setCurrentItem(mCurrentPagerPosition, true);
            mCurrentData = (Data) savedInstanceState.getSerializable(KEY_CURRENT_DATA);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initVars() {
        mAdapterHomeCategories = new AdapterHomeCategories(getChildFragmentManager());
        mSource = listObservable(new CacheStrategy.CacheStrategyBuilder(getActivity())
                .preferDefault()
                .build()
                .getStrategy());
        setServiceSubscription(dataSubscription());
    }

    private Observable<List<String>> listObservable(Observable<Data> observable) {
        return observable
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .map(new Func1<Data, Feed>() {
                    @Override
                    public Feed call(Data data) {
                        return data.getFeed();
                    }
                })
                .filter(RxJavaUtil.isNotNull)
                .flatMap(new Func1<Feed, Observable<Entry>>() {
                    @Override
                    public Observable<Entry> call(Feed feed) {
                        return Observable.from(feed.getEntries());
                    }
                })
                .filter(RxJavaUtil.isNotNull)
                .map(new Func1<Entry, String>() {
                    @Override
                    public String call(Entry entry) {
                        return entry.getCategory().getAttributes().getLabel();
                    }
                })
                .toSortedList(new Func2<String, String, Integer>() {
                    @Override
                    public Integer call(String s, String s2) {
                        return s.compareTo(s2);
                    }
                })
                .map(new Func1<List<String>, List<String>>() {
                    @Override
                    public List<String> call(List<String> strings) {
                        Set<String> set = new LinkedHashSet<>(strings);
                        String[] noDuplicates = new String[set.size()];
                        set.toArray(noDuplicates);
                        return Arrays.asList(noDuplicates);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .replay()
                .refCount();
    }

    private void initViews() {
        mBinding.vPHome.setAdapter(mAdapterHomeCategories);
        mBinding.tLHome.post(new Runnable() {
            @Override
            public void run() {
                mBinding.tLHome.setupWithViewPager(mBinding.vPHome);
            }
        });
    }

    private void initListeners() {
        mBinding.vPHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPagerPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private Subscription dataSubscription() {
        mList.clear();
        return mSource.subscribe(new Observer<List<String>>() {
            @Override
            public void onCompleted() {
                mBinding.vPHome.setCurrentItem(mCurrentPagerPosition, true);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                BusClient.getInstance().postOnUIThread(
                        new EventSnackbarMessage(getString(R.string.error_general))
                        , getActivity()
                );
            }

            @Override
            public void onNext(List<String> list) {
                mList.addAll(list);
                mAdapterHomeCategories.updateData(list);
            }
        });
    }
}
