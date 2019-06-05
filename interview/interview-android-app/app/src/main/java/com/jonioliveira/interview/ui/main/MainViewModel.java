package com.jonioliveira.interview.ui.main;

import android.text.TextUtils;

import androidx.databinding.ObservableField;

import com.jonioliveira.interview.data.DataManager;
import com.jonioliveira.interview.ui.base.BaseViewModel;
import com.jonioliveira.interview.utils.rx.SchedulerProvider;

import java.util.Calendar;


public class MainViewModel extends BaseViewModel<MainNavigator> {

    private final ObservableField<String> appVersion = new ObservableField<>();

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

    public void logout() {
        setIsLoading(true);
        getDataManager().setUserAsLoggedOut();
        setIsLoading(false);
    }

    public void onNavMenuCreated() {
        final String currentUserName = getDataManager().getCurrentUserName();
        if (!TextUtils.isEmpty(currentUserName)) {
            userName.set(currentUserName);
        }
    }

    public void updateAppVersion(String version) {
        appVersion.set(version);
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
        if (calendar.before(Calendar.getInstance())){
            btnEnabled.set(false);
        } else {
            btnEnabled.set(true);
        }
    }
}
