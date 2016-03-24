package com.santiagoalvarez.grabilityapplicanttest.util;

/**
 * Created by santiagoalvarezmonsalve on 3/24/16.
 */
public class StringUtil {

    public static boolean isNotBlank(CharSequence string) {
        if (string == null) {
            return false;
        } else {
            int i = 0;
            for (int n = string.length(); i < n; ++i) {
                if (!Character.isWhitespace(string.charAt(i))) {
                    return true;
                }
            }

            return false;
        }
    }
}
