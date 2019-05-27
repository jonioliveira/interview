package com.jonioliveira.interview.di.module;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jonioliveira.interview.data.AppDataManager;
import com.jonioliveira.interview.data.DataManager;
import com.jonioliveira.interview.data.local.prefs.AppPreferencesHelper;
import com.jonioliveira.interview.data.local.prefs.PreferencesHelper;
import com.jonioliveira.interview.data.remote.ApiHelper;
import com.jonioliveira.interview.data.remote.AppApiHelper;
import com.jonioliveira.interview.di.PreferenceInfo;
import com.jonioliveira.interview.utils.AppConstants;
import com.jonioliveira.interview.utils.rx.AppSchedulerProvider;
import com.jonioliveira.interview.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

}
