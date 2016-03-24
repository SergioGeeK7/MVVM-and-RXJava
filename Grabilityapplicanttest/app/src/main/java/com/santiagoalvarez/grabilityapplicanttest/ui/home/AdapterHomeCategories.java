package com.santiagoalvarez.grabilityapplicanttest.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.TextUtils;

import com.santiagoalvarez.grabilityapplicanttest.ui.categories.CategoriesFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by santiagoalvarezmonsalve on 3/20/16.
 */
public class AdapterHomeCategories extends FragmentStatePagerAdapter {

    private List<String> categoryLabels = new ArrayList<>();

    public AdapterHomeCategories(FragmentManager fm) {
        super(fm);
    }

    void updateData(List<String> categoryLabels) {
        this.categoryLabels.clear();
        this.categoryLabels.addAll(categoryLabels);
        notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TextUtils.isEmpty(categoryLabels.get(position)) ?
                "" : categoryLabels.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return CategoriesFragment.newInstance(categoryLabels.get(position));
    }

    @Override
    public int getCount() {
        return categoryLabels.size();
    }
}
