package com.santiagoalvarez.grabilityapplicanttest.util;

import rx.functions.Func1;

/**
 * Created by santiagoalvarezmonsalve on 3/21/16.
 */
public class RxJavaUtil {

    private RxJavaUtil() {
        throw new UnsupportedOperationException("utility class. Please don't instantiate this class");
    }

    /**
     * check nullity
     */
    public static Func1<Object, Boolean> isNotNull = new Func1<Object, Boolean>() {
        @Override
        public Boolean call(Object o) {
            return o != null;
        }
    };
}
