package com.jonioliveira.interview.ui.user;

public interface UserNavigator {

    void openLoginActivity();

    void onAddUserClick();

    void handleError(Throwable throwable);
}
