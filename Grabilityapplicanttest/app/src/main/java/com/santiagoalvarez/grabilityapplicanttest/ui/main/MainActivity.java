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
import com.santiagoalvarez.grabilityapplicanttest.model.Entry;
import com.santiagoalvarez.grabilityapplicanttest.ui.detail.DetailFragment;
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
        mToolbar = mMainBinding.tActionBar;
        setSupportActionBar(mToolbar);
    }

    private void updateToolbar(String title, boolean shouldGoBack) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(shouldGoBack);
            getToolbar().setTitle(title);
        }
    }

    private void initViews() {
        goToHome();
    }

    public void goToHome() {
        updateToolbar(getString(R.string.home_ab_title), false);
        HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HomeFragment.TAG);
        FragmentNavigator.navigateTo(getSupportFragmentManager()
                , homeFragment != null ? homeFragment : new HomeFragment()
                , R.id.container
                , new FragmentNavigatorOptions());
    }

    public void goToCategory(String categoryLabel) {
        updateToolbar(getString(R.string.categories_ab_title), false);
        //TODO
//        DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentByTag(DetailFragment.TAG);
//        FragmentNavigator.navigateTo(getSupportFragmentManager()
//                , detailFragment != null ? detailFragment : DetailFragment.newInstance(entry)
//                , R.id.container
//                , new FragmentNavigatorOptions());
    }

    public void goToDetail(Entry entry) {
        updateToolbar(entry.getImName().getLabel(), true);
        DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentByTag(DetailFragment.TAG);
        FragmentNavigator.navigateTo(getSupportFragmentManager()
                , detailFragment != null ? detailFragment : DetailFragment.newInstance(entry)
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
