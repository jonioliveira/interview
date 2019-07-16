package com.jonioliveira.interview.ui.calendar;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CalendarFragmentProvider {

    @ContributesAndroidInjector(modules = CalendarFragmentModule.class)
    abstract CalendarFragment provideCalendarFragmentFactory();
}
