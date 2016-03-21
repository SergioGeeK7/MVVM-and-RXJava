package com.santiagoalvarez.grabilityapplicanttest.ui.home;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.santiagoalvarez.grabilityapplicanttest.R;
import com.santiagoalvarez.grabilityapplicanttest.base.BaseActivity;
import com.santiagoalvarez.grabilityapplicanttest.base.BaseFragment;
import com.santiagoalvarez.grabilityapplicanttest.databinding.FragmentHomeBinding;
import com.santiagoalvarez.grabilityapplicanttest.eventbus.BusClient;
import com.santiagoalvarez.grabilityapplicanttest.eventbus.events.EventSnackbarMessage;
import com.santiagoalvarez.grabilityapplicanttest.model.Data;
import com.santiagoalvarez.grabilityapplicanttest.model.Entry;
import com.santiagoalvarez.grabilityapplicanttest.model.Feed;
import com.santiagoalvarez.grabilityapplicanttest.rest.RestClient;
import com.santiagoalvarez.grabilityapplicanttest.util.FileClient;
import com.santiagoalvarez.grabilityapplicanttest.util.RxJavaUtil;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class HomeFragment extends BaseFragment {

    public static final String TAG = HomeFragment.class.getSimpleName() + "RX";

    private FragmentHomeBinding mBinding;

    private AdapterHomeCategories mAdapterHomeCategories;
    private Observable<List<String>> data;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVars();
        initData();
    }

    private void initVars() {
        mAdapterHomeCategories = new AdapterHomeCategories(getChildFragmentManager());
    }

    private void initData() {
        Observable<Data> source;
        if (((BaseActivity) getActivity()).isConnectedToInternet()) {
            source = Observable.concat(
                    RestClient.getInstance().getPublicService().feed() /* 1. network */
                    , FileClient.getData(getActivity()) /* 2. disk */)
                    .first();

            // cache data on memory and save data to disk
            source
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(Schedulers.io())
                    .map(new Func1<Data, Data>() {
                        @Override
                        public Data call(Data data) {
                            FileClient.saveData(getActivity(), data);
                            return data;
                        }
                    })
                    .cache()
                    .subscribe();
        } else {
            source = Observable.concat(
                    FileClient.getData(getActivity()) /* 1. disk */
                    , RestClient.getInstance().getPublicService().feed() /* 2. network */)
                    .first()
                    .cache();
        }

        data = source
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
                .observeOn(AndroidSchedulers.mainThread());
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
        updateUI();
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

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void updateUI() {
        //get from memory and update UI
        setServiceSubscription(data
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onCompleted() {

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
                        mAdapterHomeCategories.updateData(list);
                    }
                }));
    }

}
