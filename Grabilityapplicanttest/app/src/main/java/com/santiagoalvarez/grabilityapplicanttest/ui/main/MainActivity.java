package com.santiagoalvarez.grabilityapplicanttest.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.google.common.eventbus.Subscribe;
import com.santiagoalvarez.grabilityapplicanttest.R;
import com.santiagoalvarez.grabilityapplicanttest.base.BaseActivity;
import com.santiagoalvarez.grabilityapplicanttest.databinding.ActivityMainBinding;
import com.santiagoalvarez.grabilityapplicanttest.eventbus.events.EventAlertDialog;
import com.santiagoalvarez.grabilityapplicanttest.eventbus.events.EventSnackbarMessage;
import com.santiagoalvarez.grabilityapplicanttest.ui.categories.CategoriesFragment;
import com.santiagoalvarez.grabilityapplicanttest.ui.home.HomeFragment;
import com.santiagoalvarez.grabilityapplicanttest.util.navigation.FragmentNavigator;
import com.santiagoalvarez.grabilityapplicanttest.util.navigation.FragmentNavigatorOptions;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding mMainBinding;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initToolbar();
        initViews();
    }

    private void initToolbar() {
        mToolbar = mMainBinding.toolbar;
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initViews() {
        goToHome();
    }

    public void goToHome() {
        getToolbar().setTitle(getString(R.string.home_ab_title));
        FragmentNavigator.navigateTo(getSupportFragmentManager()
                , new HomeFragment()
                , R.id.container
                , new FragmentNavigatorOptions());
    }

    public void goToCategories() {
        getToolbar().setTitle(getString(R.string.categories_ab_title));
        FragmentNavigator.navigateTo(getSupportFragmentManager()
                , new CategoriesFragment()
                , R.id.container
                , new FragmentNavigatorOptions());
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
