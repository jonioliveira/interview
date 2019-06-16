package com.jonioliveira.interview.data.remote;

import com.jonioliveira.interview.BuildConfig;

public final class ApiEndPoint {

    public static final String ENDPOINT_USERS_ADD = BuildConfig.USERS_URL + "/v1/user";

    public static final String ENDPOINT_USERS_LOGIN = BuildConfig.USERS_URL + "/v1/user/login";

    public static final String ENDPOINT_SLOTS_ADD = BuildConfig.INTERVIEW_URL + "/v1/slots";

    public static final String ENDPOINT_SLOTS_SCHEDULE = BuildConfig.INTERVIEW_URL + "/v1/slots/schedule";

    public static final String ENDPOINT_SLOTS_GET_DATE = BuildConfig.INTERVIEW_URL + "/v1/slots/date";

    public static final String ENDPOINT_SLOTS_GET_DATE_AND_USER = BuildConfig.INTERVIEW_URL + "/v1/slots/dateuser";

    private ApiEndPoint() {
        // This class is not publicly instantiable
    }
}
