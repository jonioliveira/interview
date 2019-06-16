package com.jonioliveira.interview.data.local.prefs;


public interface PreferencesHelper {

    Integer getCurrentUserId();

    void setCurrentUserId(Integer userId);

    String getCurrentUserName();

    void setCurrentUserName(String userName);

    Integer getCurrentUserTypeId();

    void setCurrentUserTypeId(Integer userTypeId);
}
