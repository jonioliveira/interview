package com.jonioliveira.interview.ui.calendar;

import androidx.recyclerview.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;

@Module
public class CalendarFragmentModule {
    @Provides
    LinearLayoutManager provideLinearLayoutManager(CalendarFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    CalendarAdapter provideCalendarAdapter() {
        return new CalendarAdapter();
    }
}
