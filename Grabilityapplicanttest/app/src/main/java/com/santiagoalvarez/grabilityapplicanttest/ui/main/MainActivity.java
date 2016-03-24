package com.santiagoalvarez.grabilityapplicanttest.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.common.eventbus.Subscribe;
import com.santiagoalvarez.grabilityapplicanttest.R;
import com.santiagoalvarez.grabilityapplicanttest.base.BaseActivity;
import com.santiagoalvarez.grabilityapplicanttest.databinding.ActivityMainBinding;
import com.santiagoalvarez.grabilityapplicanttest.eventbus.events.EventAlertDialog;
import com.santiagoalvarez.grabilityapplicanttest.eventbus.events.EventSnackbarMessage;
import com.santiagoalvarez.grabilityapplicanttest.model.Entry;
import com.santiagoalvarez.grabilityapplicanttest.ui.detail.DetailFragment;
import com.santiagoalvarez.grabilityapplicanttest.ui.home.HomeFragment;
import com.santiagoalvarez.grabilityapplicanttest.util.navigation.FragmentNavigator;
import com.santiagoalvarez.grabilityapplicanttest.util.navigation.FragmentNavigatorOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding mMainBinding;
    private Toolbar mToolbar;
    private List<String> titleStack = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initToolbar();
        initViews();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void navigateMainContent(Fragment frg, String title) {
        FragmentNavigator.cleanFragmentStack(getSupportFragmentManager());
        FragmentNavigator.navigateTo(getSupportFragmentManager(), frg, R.id.container, new FragmentNavigatorOptions().setAddingToStack(false));
        titleStack.clear();
        titleStack.add(title);
        updateActionBarTitle();
    }

    public void navigateToLowLevel(Fragment frg, String title) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        titleStack.add(title);
        FragmentNavigator.navigateTo(getSupportFragmentManager(), frg, R.id.container, new FragmentNavigatorOptions().setAddingToStack(true));
        updateActionBarTitle();
    }

    public void updateActionBarTitle() {
        getSupportActionBar().setTitle(titleStack.get(titleStack.size() - 1));
    }

    @Override
    public void onBackPressed() {

        if ((titleStack.size()) > 0) {
            titleStack.remove(titleStack.size() - 1);
        }
        if (titleStack.size() > 0) {
            updateActionBarTitle();
        }
        if (titleStack.size() > 1) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        super.onBackPressed();

    }

    private void initToolbar() {
        mToolbar = mMainBinding.tActionBar;
        setSupportActionBar(mToolbar);
    }

    private void initViews() {
        goToHome();
    }

    public void goToHome() {
        HomeFragment homeFragment = getSupportFragmentManager().findFragmentByTag(HomeFragment.TAG) != null ?
                (HomeFragment) getSupportFragmentManager().findFragmentByTag(HomeFragment.TAG)
                : new HomeFragment();
        navigateMainContent(homeFragment, getString(R.string.home_ab_title));
    }

    public void goToDetail(Entry entry) {
        DetailFragment detailFragment =
                getSupportFragmentManager().findFragmentByTag(DetailFragment.TAG) != null ?
                        (DetailFragment) getSupportFragmentManager().findFragmentByTag(DetailFragment.TAG)
                        : DetailFragment.newInstance(entry);
        navigateToLowLevel(detailFragment, entry.getImName().getLabel());
    }


    @Subscribe
    public void onSnackBarEvent(EventSnackbarMessage event) {
        handleSnackbarMessageEvent(event);
    }

    @Subscribe
    public void onAlertDialogEvent(EventAlertDialog event) {
        handleAlertDialogEvent(event);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }
}
