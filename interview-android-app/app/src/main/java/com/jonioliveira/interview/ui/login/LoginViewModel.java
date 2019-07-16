package com.jonioliveira.interview.ui.login;

import com.jonioliveira.interview.data.DataManager;
import com.jonioliveira.interview.data.model.api.LoginRequest;
import com.jonioliveira.interview.ui.base.BaseViewModel;
import com.jonioliveira.interview.utils.rx.SchedulerProvider;


public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void login(String name) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doLoginApiCall(new LoginRequest(name))
                .doOnSuccess(response -> getDataManager()
                        .updateUserInfo(
                                response.getName(),
                                response.getId(),
                                DataManager.UserType.fromId(response.getType())))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    setIsLoading(false);
                    getNavigator().openMainActivity();
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }




    public void onServerLoginClick() {
        getNavigator().login();
    }

    public void onAddUserClick(){
        getNavigator().openAddUser();
    }
}
