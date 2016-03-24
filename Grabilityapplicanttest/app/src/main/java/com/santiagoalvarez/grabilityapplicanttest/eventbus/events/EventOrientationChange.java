package com.santiagoalvarez.grabilityapplicanttest.eventbus.events;

/**
 * Created by santiagoalvarezmonsalve on 3/21/16.
 */
public class EventOrientationChange {

    private int orientation;

    public EventOrientationChange(int orientation){
        this.orientation = orientation;
    }

    public int getOrientation() {
        return orientation;
    }
}
