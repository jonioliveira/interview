package com.jonioliveira.interview.ui.user;

import com.jonioliveira.interview.data.DataManager;
import com.jonioliveira.interview.data.model.api.AddUserRequest;
import com.jonioliveira.interview.data.model.api.LoginRequest;
import com.jonioliveira.interview.ui.base.BaseViewModel;
import com.jonioliveira.interview.utils.AppLogger;
import com.jonioliveira.interview.utils.rx.SchedulerProvider;

public class UserViewModel extends BaseViewModel<UserNavigator> {

    public UserViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onAddUserClick(){
        getNavigator().onAddUserClick();
    }

    public void addUser(String name, int userType){
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doAddUserApiCall(new AddUserRequest(name, userType))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().openLoginActivity();
                }, throwable -> {
                    setIsLoading(false);
                    AppLogger.e(throwable, "Errror");
                    getNavigator().handleError(throwable);
                }));
    }
}
