package com.jonioliveira.interview.data.remote;

import com.jonioliveira.interview.BuildConfig;

public final class ApiEndPoint {

    public static final String ENDPOINT_USERS_ADD = BuildConfig.USERS_URL + "/v1/user";

    public static final String ENDPOINT_USERS_LOGIN = BuildConfig.USERS_URL + "/v1/user/login";

    public static final String ENDPOINT_AVAILABILITY_ADD = BuildConfig.USERS_URL + "/v1/availability";

    public static final String ENDPOINT_AVAILABILITY_GET_DATE = BuildConfig.USERS_URL + "/v1/availability/date";

    public static final String ENDPOINT_AVAILABILITY_GET_ID = BuildConfig.USERS_URL + "/v1/availability/";

    public static final String ENDPOINT_INTERVIEW_ADD = BuildConfig.USERS_URL + "/v1/interview";

    public static final String ENDPOINT_INTERVIEW_GET = BuildConfig.USERS_URL + "/v1/interview/";

    private ApiEndPoint() {
        // This class is not publicly instantiable
    }
}
