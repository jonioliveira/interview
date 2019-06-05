package com.jonioliveira.interview.data.remote;

import com.jonioliveira.interview.data.model.api.BlogResponse;
import com.jonioliveira.interview.data.model.api.LoginRequest;
import com.jonioliveira.interview.data.model.api.LoginResponse;
import com.jonioliveira.interview.data.model.api.LogoutResponse;
import com.jonioliveira.interview.data.model.api.OpenSourceResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import io.reactivex.Single;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppApiHelper implements ApiHelper {

    @Inject
    public AppApiHelper() {
    }

    @Override
    public Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_USERS_LOGIN)
                .addBodyParameter(request)
                .build()
                .getObjectSingle(LoginResponse.class);
    }

    @Override
    public Single<BlogResponse> getBlogApiCall() {
        return null;
        /*return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_BLOG)
                .build()
                .getObjectSingle(BlogResponse.class);*/
    }

    @Override
    public Single<OpenSourceResponse> getOpenSourceApiCall() {
        return null;
        /*return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_OPEN_SOURCE)
                .build()
                .getObjectSingle(OpenSourceResponse.class);*/
    }
}
