package com.jonioliveira.interview.data.remote;

import com.jonioliveira.interview.data.model.api.AddUserRequest;
import com.jonioliveira.interview.data.model.api.BlogResponse;
import com.jonioliveira.interview.data.model.api.LoginRequest;
import com.jonioliveira.interview.data.model.api.OpenSourceResponse;
import com.jonioliveira.interview.data.model.api.UserResponse;

import io.reactivex.Single;

public interface ApiHelper {

    Single<UserResponse> doLoginApiCall(LoginRequest request);

    Single<UserResponse> doAddUserApiCall(AddUserRequest request);

    Single<BlogResponse> getBlogApiCall();

    Single<OpenSourceResponse> getOpenSourceApiCall();
}
