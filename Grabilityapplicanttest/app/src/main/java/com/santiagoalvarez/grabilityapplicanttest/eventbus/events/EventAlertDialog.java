package com.santiagoalvarez.grabilityapplicanttest.eventbus.events;

import android.content.DialogInterface;

public class EventAlertDialog {

    private String title = "";
    private String message = "";
    private String positiveButtonText;
    private DialogInterface.OnClickListener positiveListener;
    private String negativeButtonText;
    private DialogInterface.OnClickListener negativeListener;

    public EventAlertDialog(String title, String message) {
        setTitle(title);
        setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPositiveButtonText() {
        return positiveButtonText;
    }

    public void setPositiveButtonText(String positiveButtonText) {
        this.positiveButtonText = positiveButtonText;
    }

    public DialogInterface.OnClickListener getPositiveListener() {
        return positiveListener;
    }

    public void setPositiveListener(DialogInterface.OnClickListener positiveListener) {
        this.positiveListener = positiveListener;
    }

    public String getNegativeButtonText() {
        return negativeButtonText;
    }

    public void setNegativeButtonText(String negativeButtonText) {
        this.negativeButtonText = negativeButtonText;
    }

    public DialogInterface.OnClickListener getNegativeListener() {
        return negativeListener;
    }

    public void setNegativeListener(DialogInterface.OnClickListener negativeListener) {
        this.negativeListener = negativeListener;
    }

}