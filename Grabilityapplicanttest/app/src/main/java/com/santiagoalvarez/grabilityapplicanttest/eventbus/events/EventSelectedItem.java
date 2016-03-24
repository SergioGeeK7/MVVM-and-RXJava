package com.santiagoalvarez.grabilityapplicanttest.eventbus.events;

import com.santiagoalvarez.grabilityapplicanttest.model.Entry;

/**
 * Created by santiagoalvarezmonsalve on 3/21/16.
 */
public class EventSelectedItem {

    private Entry entry;

    public EventSelectedItem(Entry entry) {
        this.entry = entry;
    }

    public Entry getEntry() {
        return entry;
    }
}
