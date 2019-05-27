package com.jonioliveira.interview.data;

import com.jonioliveira.interview.data.local.prefs.PreferencesHelper;
import com.jonioliveira.interview.data.model.api.BlogResponse;
import com.jonioliveira.interview.data.model.api.LoginRequest;
import com.jonioliveira.interview.data.model.api.LoginResponse;
import com.jonioliveira.interview.data.model.api.OpenSourceResponse;
import com.jonioliveira.interview.data.remote.ApiHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppDataManager implements DataManager {

    private final ApiHelper mApiHelper;

    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(PreferencesHelper preferencesHelper, ApiHelper apiHelper) {
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request) {
        return mApiHelper.doServerLoginApiCall(request);
    }

    @Override
    public Single<BlogResponse> getBlogApiCall() {
        return mApiHelper.getBlogApiCall();
    }

    @Override
    public Long getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(Long userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public String getCurrentUserName() {
        return mPreferencesHelper.getCurrentUserName();
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPreferencesHelper.setCurrentUserName(userName);
    }

    @Override
    public int getCurrentUserTypeId() {
        return mPreferencesHelper.getCurrentUserTypeId();
    }

    @Override
    public void setCurrentUserTypeId(int userTypeId) {
        mPreferencesHelper.setCurrentUserTypeId(userTypeId);
    }

    @Override
    public Single<OpenSourceResponse> getOpenSourceApiCall() {
        return mApiHelper.getOpenSourceApiCall();
    }

    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(null, 0, UserType.NULL);
    }

    @Override
    public void updateUserInfo(String name, int userId, UserType userTypeId) {

        setCurrentUserId((long) userId);
        setCurrentUserName(name);
        setCurrentUserTypeId(userTypeId.getType());
    }
}
