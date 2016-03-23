package com.santiagoalvarez.grabilityapplicanttest.ui.detail;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.santiagoalvarez.grabilityapplicanttest.R;
import com.santiagoalvarez.grabilityapplicanttest.base.BaseFragment;
import com.santiagoalvarez.grabilityapplicanttest.databinding.FragmentDetailBinding;
import com.santiagoalvarez.grabilityapplicanttest.model.Entry;
import com.santiagoalvarez.grabilityapplicanttest.ui.main.MainActivity;

public class DetailFragment extends BaseFragment {

    public static final String TAG = DetailFragment.class.getSimpleName();

    private static final String ARG_ENTRY = "ARG_ENTRY";
    private Entry mEntry;

    private FragmentDetailBinding mBinding;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(Entry entry) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ENTRY, entry);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVars();
    }

    private void initVars() {
        if (getArguments() != null) {
            mEntry = (Entry) getArguments().getSerializable(ARG_ENTRY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
        initListeners();
        return mBinding.getRoot();
    }

    private void initListeners() {
        mBinding.bDetailBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink(Uri.parse(mEntry.getLink().getAttributes().getHref()));
            }
        });

        mBinding.tVDetailVisitWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink(Uri.parse(mEntry.getImArtist().getAttributes().getHref()));
            }
        });

        mBinding.tVDetailSummaryMoreOrLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSummaryDialog();
            }
        });

        mBinding.tVDetailCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).goToCategory(mEntry.getCategory().getAttributes().getLabel());
            }
        });
    }

    private void openLink(Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        startActivity(intent);
    }

    private void openSummaryDialog() {
        DialogFragment dialogFragment = DetailSummaryDialog.newInstance(mEntry);
        dialogFragment.show(getChildFragmentManager(), DetailSummaryDialog.TAG);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.setEntry(mEntry);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            mEntry = (Entry) savedInstanceState.getSerializable(ARG_ENTRY);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(ARG_ENTRY, mEntry);
        super.onSaveInstanceState(outState);
    }
}
