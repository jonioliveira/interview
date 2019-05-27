package com.jonioliveira.interview.ui.user;

import com.jonioliveira.interview.data.DataManager;
import com.jonioliveira.interview.ui.base.BaseViewModel;
import com.jonioliveira.interview.utils.rx.SchedulerProvider;

public class UserViewModel extends BaseViewModel<UserNavigator> {

    public UserViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onAddUserClick(){
        getNavigator().openLoginActivity();
    }
}
