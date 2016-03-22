package com.santiagoalvarez.grabilityapplicanttest.eventbus.events;

/**
 * Created by santiagoalvarezmonsalve on 3/21/16.
 */
public class EventSelectedItem {

    private int selectedPosition;

    public EventSelectedItem(int position) {
        selectedPosition = position;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }
}
