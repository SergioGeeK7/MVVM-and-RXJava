package com.santiagoalvarez.grabilityapplicanttest.ui.init;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;

import com.santiagoalvarez.grabilityapplicanttest.R;
import com.santiagoalvarez.grabilityapplicanttest.ui.main.MainActivity;

public class InitActivity extends AppCompatActivity {

    private static final long SPLASH_TIME = 500;

    private final CountDownTimer splashTimer = new CountDownTimer(SPLASH_TIME, 1000) {
        @Override
        public void onTick(long l) {

        }

        @Override
        public void onFinish() {
            goToMain();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        goToSplash();
    }

    @Override
    protected void onResume() {
        super.onResume();
        splashTimer.start();
    }

    @Override
    protected void onPause() {
        splashTimer.cancel();
        super.onPause();
    }

    private void goToSplash() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new SplashFragment())
                .addToBackStack(null)
                .commit();
    }

    public void goToMain() {
        Intent i = new Intent(InitActivity.this, MainActivity.class);
        startActivity(i);
    }
}
