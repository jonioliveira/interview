package com.jonioliveira.interview.data.remote;

import com.jonioliveira.interview.data.model.api.AddUserRequest;
import com.jonioliveira.interview.data.model.api.BlogResponse;
import com.jonioliveira.interview.data.model.api.LoginRequest;
import com.jonioliveira.interview.data.model.api.OpenSourceResponse;
import com.jonioliveira.interview.data.model.api.UserResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppApiHelper implements ApiHelper {

    @Inject
    public AppApiHelper() {
    }

    @Override
    public Single<UserResponse> doLoginApiCall(LoginRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_USERS_LOGIN)
                .addApplicationJsonBody(request)
                .build()
                .getObjectSingle(UserResponse.class);
    }

    @Override
    public Single<UserResponse> doAddUserApiCall(AddUserRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_USERS_ADD)
                .addApplicationJsonBody(request)
                .build()
                .getObjectSingle(UserResponse.class);
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
