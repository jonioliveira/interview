package com.jonioliveira.interview.ui.login;

public interface LoginNavigator {

    void handleError(Throwable throwable);

    void login();

    void openMainActivity();

    void openAddUser();
}
