package com.jonioliveira.interview.ui.calendar.dialog;

import com.jonioliveira.interview.data.DataManager;
import com.jonioliveira.interview.ui.base.BaseViewModel;
import com.jonioliveira.interview.utils.rx.SchedulerProvider;

public class CalendarDialogViewModel extends BaseViewModel<CalendarDialogCallback> {

    public CalendarDialogViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onNoClick() {
        getNavigator().dismissDialog();
    }

    public void onYesClick() {
        getNavigator().onSubmit();
    }
}
