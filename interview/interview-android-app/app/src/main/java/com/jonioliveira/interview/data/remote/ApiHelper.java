package com.jonioliveira.interview.data.remote;

import com.jonioliveira.interview.data.model.api.BlogResponse;
import com.jonioliveira.interview.data.model.api.LoginRequest;
import com.jonioliveira.interview.data.model.api.LoginResponse;
import com.jonioliveira.interview.data.model.api.LogoutResponse;
import com.jonioliveira.interview.data.model.api.OpenSourceResponse;
import io.reactivex.Single;

public interface ApiHelper {

    Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request);

    Single<BlogResponse> getBlogApiCall();

    Single<OpenSourceResponse> getOpenSourceApiCall();
}
