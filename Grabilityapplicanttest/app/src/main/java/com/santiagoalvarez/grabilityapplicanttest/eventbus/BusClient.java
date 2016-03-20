package com.santiagoalvarez.grabilityapplicanttest.eventbus;

/**
 * Created by santiagoalvarezmonsalve on 3/19/16.
 */
public final class BusClient {

    private static final UIBus BUS = new UIBus();

    private BusClient() {
        throw new UnsupportedOperationException("Please don't instantiate this class");
    }

    public static UIBus getInstance() {
        return BUS;
    }
}
