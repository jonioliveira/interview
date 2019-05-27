package com.jonioliveira.interview.data;

import com.jonioliveira.interview.data.local.prefs.PreferencesHelper;
import com.jonioliveira.interview.data.remote.ApiHelper;

public interface DataManager extends PreferencesHelper, ApiHelper {

    void setUserAsLoggedOut();

    void updateUserInfo(String name, int userId, UserType userTypeId);

    enum UserType {
        NULL(0),
        INTERVIEW(1),
        CANDIDATE(2);

        private final int mType;

        UserType(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
