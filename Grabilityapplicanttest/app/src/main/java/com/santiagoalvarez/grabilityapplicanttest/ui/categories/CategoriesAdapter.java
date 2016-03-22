package com.santiagoalvarez.grabilityapplicanttest.ui.categories;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.santiagoalvarez.grabilityapplicanttest.R;
import com.santiagoalvarez.grabilityapplicanttest.databinding.CategoryItemBinding;
import com.santiagoalvarez.grabilityapplicanttest.eventbus.BusClient;
import com.santiagoalvarez.grabilityapplicanttest.eventbus.events.EventSelectedItem;
import com.santiagoalvarez.grabilityapplicanttest.model.Entry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by santiagoalvarezmonsalve on 3/21/16.
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.DataBoundViewHolder> {

    private Context mContext;
    private List<Entry> mEntries = new ArrayList<>();

    static Object DATA_INVALIDATION = new Object();
    private RecyclerView mRecyclerView;

    public CategoriesAdapter(Context context) {
        mContext = context;
        mRecyclerView = new RecyclerView(mContext);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mRecyclerView = null;
    }

    @Override
    public DataBoundViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final DataBoundViewHolder holder = DataBoundViewHolder.create(LayoutInflater.from(mContext), parent);
        holder.getBinding().addOnRebindCallback(new OnRebindCallback() {
            @Override
            public boolean onPreBind(ViewDataBinding binding) {
                return mRecyclerView != null && mRecyclerView.isComputingLayout();
            }

            @Override
            public void onCanceled(ViewDataBinding binding) {
                if (mRecyclerView == null || mRecyclerView.isComputingLayout()) {
                    return;
                }
                int pos = mRecyclerView.getChildAdapterPosition(binding.getRoot());
                if (pos != RecyclerView.NO_POSITION) {
                    notifyItemChanged(pos, DATA_INVALIDATION);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(final DataBoundViewHolder holder, final int position) {
        holder.bindTo(mEntries.get(position));
        holder.getBinding().lLItemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemChanged(position);
                BusClient.getInstance().postOnUIThread(new EventSelectedItem(position), (Activity) mContext);
            }
        });
    }

    @Override
    public void onBindViewHolder(DataBoundViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        if (isForDataBinding(payloads)) {
            holder.getBinding().executePendingBindings();
        } else {
            super.onBindViewHolder(holder, position, payloads);
        }
    }

    @Override
    public int getItemCount() {
        return mEntries.size();
    }

    private boolean isForDataBinding(List<Object> payloads) {
        if (payloads == null || payloads.size() == 0) {
            return false;
        }
        for (Object obj : payloads) {
            if (obj != DATA_INVALIDATION) {
                return false;
            }
        }
        return true;
    }

    public void insertDataItem(int pos, Entry entry) {
        if (mEntries.size() > pos) {
            mEntries.set(pos, entry);
            notifyItemChanged(pos);
        } else {
            mEntries.add(pos, entry);
            notifyItemInserted(pos);
        }
    }

    public static class DataBoundViewHolder extends RecyclerView.ViewHolder {

        public static DataBoundViewHolder create(LayoutInflater layoutInflater, ViewGroup parent) {
            return new DataBoundViewHolder(DataBindingUtil.<CategoryItemBinding>inflate(layoutInflater
                    , R.layout.category_item, parent, false));
        }

        private CategoryItemBinding mBinding;

        private DataBoundViewHolder(CategoryItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public CategoryItemBinding getBinding() {
            return mBinding;
        }

        public void bindTo(Entry entry) {
            mBinding.setEntry(entry);
            mBinding.executePendingBindings();
        }
    }
}
