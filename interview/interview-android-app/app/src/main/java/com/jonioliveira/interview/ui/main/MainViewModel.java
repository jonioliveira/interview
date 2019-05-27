package com.jonioliveira.interview.ui.main;

import android.text.TextUtils;

import androidx.databinding.ObservableField;

import com.jonioliveira.interview.data.DataManager;
import com.jonioliveira.interview.ui.base.BaseViewModel;
import com.jonioliveira.interview.utils.rx.SchedulerProvider;


public class MainViewModel extends BaseViewModel<MainNavigator> {

    private final ObservableField<String> appVersion = new ObservableField<>();

    private final ObservableField<String> userName = new ObservableField<>();

    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public ObservableField<String> getAppVersion() {
        return appVersion;
    }

    public ObservableField<String> getUserName() {
        return userName;
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
}
