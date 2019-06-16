package com.jonioliveira.interview.data.remote;

import com.jonioliveira.interview.data.model.api.AddSlotRequest;
import com.jonioliveira.interview.data.model.api.AddUserRequest;
import com.jonioliveira.interview.data.model.api.LoginRequest;
import com.jonioliveira.interview.data.model.api.ScheduleSlotRequest;
import com.jonioliveira.interview.data.model.api.SlotsForDayAndUserRequest;
import com.jonioliveira.interview.data.model.api.SlotsForDayRequest;
import com.jonioliveira.interview.data.model.api.SlotsResponse;
import com.jonioliveira.interview.data.model.api.UserResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public interface ApiHelper {

    Single<UserResponse> doLoginApiCall(LoginRequest request);

    Single<UserResponse> doAddUserApiCall(AddUserRequest request);

    Single<List<SlotsResponse>> doGetSlotsForDayByUser(SlotsForDayAndUserRequest request);

    Single<List<SlotsResponse>> doGetSlotsForDay(SlotsForDayRequest request);

    Single<SlotsResponse> doSheduleSlot(ScheduleSlotRequest request);

    Single<List<SlotsResponse>> doAddSlotRequest(AddSlotRequest[] request);

}
