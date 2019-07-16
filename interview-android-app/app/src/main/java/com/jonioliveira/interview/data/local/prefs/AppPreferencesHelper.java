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
    public Integer getCurrentUserId() {
        int userId = mPrefs.getInt(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX);
        return userId == AppConstants.NULL_INDEX ? null : userId;
    }

    @Override
    public void setCurrentUserId(Integer userId) {
        int id = userId == null ? AppConstants.NULL_INDEX : userId;
        mPrefs.edit().putInt(PREF_KEY_CURRENT_USER_ID, id).apply();
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
    public Integer getCurrentUserTypeId() {
        int id = mPrefs.getInt(PREF_KEY_CURRENT_USER_TYPE_ID, AppConstants.NULL_INDEX);
        return id == AppConstants.NULL_INDEX ? null : id;
    }

    @Override
    public void setCurrentUserTypeId(Integer userTypeId) {
        int id = userTypeId == null ? AppConstants.NULL_INDEX : userTypeId;
        mPrefs.edit().putInt(PREF_KEY_CURRENT_USER_TYPE_ID, id).apply();
    }
}
