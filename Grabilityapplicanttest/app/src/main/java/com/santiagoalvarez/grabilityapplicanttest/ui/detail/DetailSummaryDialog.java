package com.santiagoalvarez.grabilityapplicanttest.ui.detail;


import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.santiagoalvarez.grabilityapplicanttest.R;
import com.santiagoalvarez.grabilityapplicanttest.databinding.DialogDetailSummaryBinding;
import com.santiagoalvarez.grabilityapplicanttest.model.Entry;

public class DetailSummaryDialog extends DialogFragment {

    public static final String TAG = DetailSummaryDialog.class.getSimpleName();

    private static final String ARG_ENTRY = "ARG_ENTRY";
    private Entry mEntry;

    private DialogDetailSummaryBinding mBinding;

    public DetailSummaryDialog() {
        // Required empty public constructor
    }

    public static DetailSummaryDialog newInstance(Entry entry) {
        DetailSummaryDialog fragment = new DetailSummaryDialog();
        Bundle args = new Bundle();
        args.putSerializable(ARG_ENTRY, entry);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_detail_summary, container, false);
        Entry entry = new Entry();

        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
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
