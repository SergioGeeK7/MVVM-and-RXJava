package com.santiagoalvarez.grabilityapplicanttest.ui.categories;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.santiagoalvarez.grabilityapplicanttest.R;
import com.santiagoalvarez.grabilityapplicanttest.databinding.FragmentCategoriesBinding;
import com.santiagoalvarez.grabilityapplicanttest.model.Data;
import com.santiagoalvarez.grabilityapplicanttest.rest.RestClient;

import rx.Observer;

public class CategoriesFragment extends Fragment {
    private static final String TAG = "CategoriesFragment";

    private FragmentCategoriesBinding mBinding;

    public CategoriesFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false);
        initListeners();
        return mBinding.getRoot();
    }

    private void initListeners() {
        mBinding.sRCategories.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
    }

    private void initData() {
        RestClient.getInstance().getPublicService()
                .feed()
                .subscribe(new Observer<Data>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Data data) {
                        if (data != null) {
                            Log.d(TAG, "onNext: ");
                        }
                    }
                });
    }
}
