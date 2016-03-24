package com.santiagoalvarez.grabilityapplicanttest.eventbus.events;

import android.view.View;

public class EventSnackbarMessage {

    private String text;
    private String actionLabel;
    private String mCancelText;
    private View.OnClickListener onActionListener;
    private View.OnAttachStateChangeListener eventListener;

    public EventSnackbarMessage() {
    }

    public EventSnackbarMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getActionLabel() {
        return actionLabel;
    }

    public void setActionLabel(String actionLabel) {
        this.actionLabel = actionLabel;
    }

    public String getmCancelText() {
        return mCancelText;
    }

    public void setmCancelText(String mCancelText) {
        this.mCancelText = mCancelText;
    }

    public View.OnClickListener getOnActionListener() {
        return onActionListener;
    }

    public void setOnActionListener(View.OnClickListener onActionListener) {
        this.onActionListener = onActionListener;
    }

    public View.OnAttachStateChangeListener getEventListener() {
        return eventListener;
    }

    public void setEventListener(View.OnAttachStateChangeListener eventListener) {
        this.eventListener = eventListener;
    }
}
