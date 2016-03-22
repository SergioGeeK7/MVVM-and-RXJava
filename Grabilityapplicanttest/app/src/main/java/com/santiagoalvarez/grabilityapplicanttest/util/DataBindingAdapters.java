package com.santiagoalvarez.grabilityapplicanttest.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.santiagoalvarez.grabilityapplicanttest.R;

public class DataBindingAdapters {

    public static final String TAG = DataBindingAdapters.class.getSimpleName();

    @BindingAdapter("app:src")
    public static void setImageResource(ImageView imageView, String url) {
        Glide.with(imageView.getContext().getApplicationContext())
                .load(url)
                .placeholder(R.drawable.ic_image_black_48dp)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }
}