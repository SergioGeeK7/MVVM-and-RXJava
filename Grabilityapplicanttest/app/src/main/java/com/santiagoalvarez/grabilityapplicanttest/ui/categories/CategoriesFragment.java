package com.santiagoalvarez.grabilityapplicanttest.ui.categories;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.santiagoalvarez.grabilityapplicanttest.R;
import com.santiagoalvarez.grabilityapplicanttest.base.BaseActivity;
import com.santiagoalvarez.grabilityapplicanttest.base.BaseFragment;
import com.santiagoalvarez.grabilityapplicanttest.databinding.FragmentCategoriesBinding;
import com.santiagoalvarez.grabilityapplicanttest.eventbus.events.EventSelectedItem;
import com.santiagoalvarez.grabilityapplicanttest.model.Data;
import com.santiagoalvarez.grabilityapplicanttest.model.Entry;
import com.santiagoalvarez.grabilityapplicanttest.model.Feed;
import com.santiagoalvarez.grabilityapplicanttest.rest.RestClient;
import com.santiagoalvarez.grabilityapplicanttest.ui.main.MainActivity;
import com.santiagoalvarez.grabilityapplicanttest.util.FileClient;
import com.santiagoalvarez.grabilityapplicanttest.util.GridItemDecoration;
import com.santiagoalvarez.grabilityapplicanttest.util.RxJavaUtil;
import com.squareup.otto.Subscribe;

import java.util.concurrent.atomic.AtomicInteger;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class CategoriesFragment extends BaseFragment {

    private static final String TAG = "CategoriesFragment";
    public static final String ARG_CATEGORY_LABEL = "ARG_CATEGORY_LABEL";
    private String mCurrentCategory;

    private Observable<Entry> data;
    private FragmentCategoriesBinding mBinding;
    private CategoriesAdapter mCategoriesAdapter;
    private GridLayoutManager mLayoutManager;
    private boolean mIsTablet;

    public CategoriesFragment() {
    }

    public static CategoriesFragment newInstance(String categoryLabel) {
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY_LABEL, categoryLabel);
        CategoriesFragment fragment = new CategoriesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVars();
        initData();
    }

    private void initVars() {
        if (getArguments() != null) {
            mCurrentCategory = getArguments().getString(ARG_CATEGORY_LABEL);
        }
        mIsTablet = getResources().getBoolean(R.bool.is_tablet);
        mCategoriesAdapter = new CategoriesAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false);
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
        mBinding.rVCategories.setAdapter(mCategoriesAdapter);
        mLayoutManager = new GridLayoutManager(getActivity()
                , mIsTablet ? 3 : 1
                , LinearLayoutManager.VERTICAL, false);
        mBinding.rVCategories.setLayoutManager(mLayoutManager);
        mBinding.rVCategories.addItemDecoration(new GridItemDecoration(getResources().getDimensionPixelSize(R.dimen.default_item_decorator_space)));
        mBinding.rVCategories.setItemAnimator(new DefaultItemAnimator());
    }

    private void initListeners() {
        mBinding.sRCategories.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateUI();
            }
        });
    }

    private void updateUI() {
        final AtomicInteger counter = new AtomicInteger(0);
        setServiceSubscription(data
                .subscribe(new Observer<Entry>() {
                    @Override
                    public void onCompleted() {
                        mBinding.sRCategories.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Entry entry) {
                        mCategoriesAdapter.insertDataItem(counter.getAndIncrement(), entry);
                    }
                }));
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
                .filter(new Func1<Entry, Boolean>() {
                    @Override
                    public Boolean call(Entry entry) {
                        return entry != null
                                && entry.getCategory().getAttributes().getLabel()
                                .equals(mCurrentCategory);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Subscribe
    public void onItemSelected(EventSelectedItem event) {
        ((MainActivity) getActivity()).goToDetail(event.getEntry());
    }
}
