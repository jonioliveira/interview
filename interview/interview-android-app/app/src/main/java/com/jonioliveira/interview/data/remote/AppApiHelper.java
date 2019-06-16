package com.jonioliveira.interview.data.remote;

import com.google.gson.JsonArray;
import com.jonioliveira.interview.data.model.api.AddSlotRequest;
import com.jonioliveira.interview.data.model.api.AddUserRequest;
import com.jonioliveira.interview.data.model.api.LoginRequest;
import com.jonioliveira.interview.data.model.api.ScheduleSlotRequest;
import com.jonioliveira.interview.data.model.api.SlotsForDayAndUserRequest;
import com.jonioliveira.interview.data.model.api.SlotsForDayRequest;
import com.jonioliveira.interview.data.model.api.SlotsResponse;
import com.jonioliveira.interview.data.model.api.UserResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.ArrayList;
import java.util.List;

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
    public Single<List<SlotsResponse>> doGetSlotsForDayByUser(SlotsForDayAndUserRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SLOTS_GET_DATE_AND_USER)
                .addApplicationJsonBody(request)
                .build()
                .getObjectListSingle(SlotsResponse.class);
    }

    @Override
    public Single<List<SlotsResponse>> doGetSlotsForDay(SlotsForDayRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SLOTS_GET_DATE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectListSingle(SlotsResponse.class);
    }

    @Override
    public Single<SlotsResponse> doSheduleSlot(ScheduleSlotRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SLOTS_SCHEDULE)
                .addApplicationJsonBody(request)
                .build()
                .getObjectSingle(SlotsResponse.class);
    }

    @Override
    public Single<List<SlotsResponse>> doAddSlotRequest(AddSlotRequest[] request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SLOTS_ADD)
                .addApplicationJsonBody(request)
                .build()
                .getObjectListSingle(SlotsResponse.class);
    }
}
