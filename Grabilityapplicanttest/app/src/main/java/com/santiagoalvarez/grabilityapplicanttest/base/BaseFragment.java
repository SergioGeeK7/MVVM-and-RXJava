package com.santiagoalvarez.grabilityapplicanttest.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.santiagoalvarez.grabilityapplicanttest.eventbus.BusClient;

import rx.Subscription;

public class BaseFragment extends Fragment {

    private Subscription serviceSubscription;

    public Subscription getServiceSubscription() {
        return serviceSubscription;
    }

    public void setServiceSubscription(Subscription serviceSubscription) {
        this.serviceSubscription = serviceSubscription;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        BusClient.getInstance().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        BusClient.getInstance().unregister(this);
        if (getServiceSubscription() != null) {
            getServiceSubscription().unsubscribe();
        }
    }

}
