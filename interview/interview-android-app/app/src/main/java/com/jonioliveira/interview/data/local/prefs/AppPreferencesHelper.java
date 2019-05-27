package com.jonioliveira.interview.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.jonioliveira.interview.di.PreferenceInfo;
import com.jonioliveira.interview.utils.AppConstants;
import javax.inject.Inject;

public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";

    private static final String PREF_KEY_CURRENT_USER_TYPE_ID = "PREF_KEY_CURRENT_USER_TYPE_ID";

    private static final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";


    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public Long getCurrentUserId() {
        long userId = mPrefs.getLong(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX);
        return userId == AppConstants.NULL_INDEX ? null : userId;
    }

    @Override
    public void setCurrentUserId(Long userId) {
        long id = userId == null ? AppConstants.NULL_INDEX : userId;
        mPrefs.edit().putLong(PREF_KEY_CURRENT_USER_ID, id).apply();
    }

    @Override
    public String getCurrentUserName() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, null);
    }

    @Override
    public void setCurrentUserName(String userName) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply();
    }

    @Override
    public int getCurrentUserTypeId() {
        return mPrefs.getInt(PREF_KEY_CURRENT_USER_TYPE_ID, 0);
    }

    @Override
    public void setCurrentUserTypeId(int userTypeId) {
        mPrefs.edit().putInt(PREF_KEY_CURRENT_USER_TYPE_ID, userTypeId).apply();
    }
}
