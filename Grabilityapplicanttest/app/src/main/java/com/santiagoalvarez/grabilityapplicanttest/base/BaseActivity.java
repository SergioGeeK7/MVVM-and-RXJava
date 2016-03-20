package com.santiagoalvarez.grabilityapplicanttest.base;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;

import com.santiagoalvarez.grabilityapplicanttest.R;
import com.santiagoalvarez.grabilityapplicanttest.eventbus.BusClient;
import com.santiagoalvarez.grabilityapplicanttest.eventbus.events.EventAlertDialog;
import com.santiagoalvarez.grabilityapplicanttest.eventbus.events.EventSnackbarMessage;

import rx.Subscription;

public class BaseActivity extends AppCompatActivity {

    private Subscription serviceSubscription;

    public Subscription getServiceSubscription() {
        return serviceSubscription;
    }

    public void setServiceSubscription(Subscription serviceSubscription) {
        this.serviceSubscription = serviceSubscription;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        BusClient.getInstance().register(this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        BusClient.getInstance().unregister(this);
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        clearKeyboardFromScreen();
        super.onBackPressed();
    }

    public void handleSnackbarMessageEvent(EventSnackbarMessage event) {
        Snackbar snack = Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), event.getText(), Snackbar.LENGTH_LONG);

        if (event.getOnActionListener() != null) {
            snack.setAction(event.getActionLabel(), event.getOnActionListener())
                    .setActionTextColor(getResources().getColor(R.color.colorPrimary));
        }
        if (event.getEventListener() != null) {
            snack.getView().addOnAttachStateChangeListener(event.getEventListener());
        }
        snack.show();
    }

    public void handleAlertDialogEvent(EventAlertDialog alert) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(alert.getTitle())
                .setMessage(alert.getMessage())
                .setPositiveButton(
                        TextUtils.isEmpty(alert.getPositiveButtonText()) ?
                                getString(android.R.string.ok)
                                : alert.getPositiveButtonText()
                        , alert.getPositiveListener() == null ?
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                } : alert.getPositiveListener());

        if (alert.getNegativeListener() != null) {
            builder.setNegativeButton(
                    TextUtils.isEmpty(alert.getNegativeButtonText()) ?
                            getString(android.R.string.cancel)
                            : alert.getNegativeButtonText()
                    , alert.getNegativeListener() == null ?
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            } : alert.getNegativeListener());
        }

        builder.show();
    }

    public void clearKeyboardFromScreen() {
        if (getWindow().getCurrentFocus() != null) {
            InputMethodManager imm =
                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
        }
    }

}