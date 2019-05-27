package com.jonioliveira.interview.data.local.prefs;


public interface PreferencesHelper {

    Long getCurrentUserId();

    void setCurrentUserId(Long userId);

    String getCurrentUserName();

    void setCurrentUserName(String userName);

    int getCurrentUserTypeId();

    void setCurrentUserTypeId(int userTypeId);
}
