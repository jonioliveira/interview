package com.jonioliveira.interview.ui.calendar.dialog;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CalendarDialogProvider {

    @ContributesAndroidInjector
    abstract CalendarDialog provideRateUsDialogFactory();
}
