package com.jonioliveira.interview.utils;

import com.rx2androidnetworking.Rx2ANRequest;

import java.net.PortUnreachableException;
import java.time.Year;

public final class AppConstants {

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;

    public static final long NULL_INDEX = -1L;

    public static final String PREF_NAME = "interview_pref";

    public static final String STATUS_CODE_FAILED = "failed";

    public static final String STATUS_CODE_SUCCESS = "success";

    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    public static final String YEAR = "YEAR";

    public static final String MONTH = "MONTH";

    public static final String DAY_OF_MONTH = "DAY_OF_MONTH";

    private AppConstants() {
        // This utility class is not publicly instantiable
    }
}
