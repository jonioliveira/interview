package com.jonioliveira.interview.di.builder;

import com.jonioliveira.interview.ui.calendar.CalendarFragmentProvider;
import com.jonioliveira.interview.ui.calendar.dialog.CalendarDialogProvider;
import com.jonioliveira.interview.ui.login.LoginActivity;
import com.jonioliveira.interview.ui.main.MainActivity;
import com.jonioliveira.interview.ui.user.UserActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector
    abstract UserActivity bindUserActivity();

    @ContributesAndroidInjector(modules = {
            CalendarDialogProvider.class,
            CalendarFragmentProvider.class})
    abstract MainActivity bindMainActivity();

}
