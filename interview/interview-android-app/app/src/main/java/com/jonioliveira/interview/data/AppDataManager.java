package com.jonioliveira.interview.data;

import com.jonioliveira.interview.data.local.prefs.PreferencesHelper;
import com.jonioliveira.interview.data.model.api.AddSlotRequest;
import com.jonioliveira.interview.data.model.api.AddUserRequest;
import com.jonioliveira.interview.data.model.api.LoginRequest;
import com.jonioliveira.interview.data.model.api.ScheduleSlotRequest;
import com.jonioliveira.interview.data.model.api.SlotsForDayAndUserRequest;
import com.jonioliveira.interview.data.model.api.SlotsForDayRequest;
import com.jonioliveira.interview.data.model.api.SlotsResponse;
import com.jonioliveira.interview.data.model.api.SlotsWithUserResponse;
import com.jonioliveira.interview.data.model.api.UserResponse;
import com.jonioliveira.interview.data.remote.ApiHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppDataManager implements DataManager {

    private final ApiHelper apiHelper;

    private final PreferencesHelper preferencesHelper;


    @Inject
    public AppDataManager(PreferencesHelper preferencesHelper, ApiHelper apiHelper) {
        this.preferencesHelper = preferencesHelper;
        this.apiHelper = apiHelper;
    }

    @Override
    public Single<UserResponse> doLoginApiCall(LoginRequest request) {
        return apiHelper.doLoginApiCall(request);
    }

    @Override
    public Single<UserResponse> doAddUserApiCall(AddUserRequest request) {
        return apiHelper.doAddUserApiCall(request);
    }

    @Override
    public Single<List<SlotsResponse>> doGetSlotsForDayByUser(SlotsForDayAndUserRequest request) {
        return apiHelper.doGetSlotsForDayByUser(request);
    }

    @Override
    public Single<List<SlotsWithUserResponse>> doGetSlotsForDay(SlotsForDayRequest request) {
        return apiHelper.doGetSlotsForDay(request);
    }

    @Override
    public Single<SlotsResponse> doSheduleSlot(ScheduleSlotRequest request) {
        return apiHelper.doSheduleSlot(request);
    }

    @Override
    public Single<List<SlotsResponse>> doAddSlotRequest(AddSlotRequest[] request) {
        return apiHelper.doAddSlotRequest(request);
    }

    @Override
    public Integer getCurrentUserId() {
        return preferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(Integer userId) {
        preferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public String getCurrentUserName() {
        return preferencesHelper.getCurrentUserName();
    }

    @Override
    public void setCurrentUserName(String userName) {
        preferencesHelper.setCurrentUserName(userName);
    }

    @Override
    public Integer getCurrentUserTypeId() {
        return preferencesHelper.getCurrentUserTypeId();
    }

    @Override
    public void setCurrentUserTypeId(Integer userTypeId) {
        preferencesHelper.setCurrentUserTypeId(userTypeId);
    }

    @Override
    public void setUserAsLoggedOut() {
        updateUserInfo(null, 0, UserType.NULL);
    }

    @Override
    public void updateUserInfo(String name, int userId, UserType userTypeId) {
        setCurrentUserId(userId);
        setCurrentUserName(name);
        setCurrentUserTypeId(userTypeId.getType());
    }
}
