package com.jonioliveira.interview.ui.main;

import android.text.TextUtils;

import androidx.databinding.ObservableField;

import com.jonioliveira.interview.data.DataManager;
import com.jonioliveira.interview.data.model.UserTypeEnum;
import com.jonioliveira.interview.ui.base.BaseViewModel;
import com.jonioliveira.interview.utils.rx.SchedulerProvider;

import java.util.Calendar;


public class MainViewModel extends BaseViewModel<MainNavigator> {

    private final ObservableField<String> appVersion = new ObservableField<>();

    private final ObservableField<String> btnText = new ObservableField<>();

    private final ObservableField<String> userName = new ObservableField<>();

    private final ObservableField<Boolean> btnEnabled = new ObservableField<>();

    private Calendar calendar;

    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        calendar = Calendar.getInstance();
    }

    public ObservableField<String> getAppVersion() {
        return appVersion;
    }

    public ObservableField<String> getUserName() {
        return userName;
    }

    public ObservableField<Boolean> getBtnEnabled(){
        return btnEnabled;
    }

    public ObservableField<String> getBtnText() {
        return btnText;
    }

    public void logout() {
        setIsLoading(true);
        getDataManager().setUserAsLoggedOut();
        setIsLoading(false);
        getNavigator().openLoginActivity();
    }

    public void onNavMenuCreated() {
        final String currentUserName = getDataManager().getCurrentUserName();
        if (!TextUtils.isEmpty(currentUserName)) {
            String username = "Olá! " + currentUserName.substring(0, 1).toUpperCase() + currentUserName.substring(1);
            userName.set(username);
        }
    }

    public void updateAppVersion(String version) {
        appVersion.set(version);
    }

    public void updateBtnText(String set, String see){
        UserTypeEnum userType = UserTypeEnum.fromValue(getDataManager().getCurrentUserTypeId());
        if (userType == UserTypeEnum.INTERVIEWER) {
            btnText.set(set);
        }else {
            btnText.set(see);
        }
    }

    public void updateBtnState(boolean value){
        btnEnabled.set(value);
    }

    public void onAvailabilityBtnClick(){
        getNavigator().openCalendarDayView(calendar);
    }

    public void onDateChange(int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        if (calendar.before(Calendar.getInstance()) ||
                (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                        calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)){
            btnEnabled.set(false);
        } else {
            btnEnabled.set(true);
        }
    }
}
