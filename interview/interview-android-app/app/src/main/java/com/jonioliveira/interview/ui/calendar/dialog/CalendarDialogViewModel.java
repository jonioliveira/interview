package com.jonioliveira.interview.ui.calendar.dialog;

import androidx.databinding.ObservableField;

import com.jonioliveira.interview.data.DataManager;
import com.jonioliveira.interview.data.model.UserTypeEnum;
import com.jonioliveira.interview.ui.base.BaseViewModel;
import com.jonioliveira.interview.utils.rx.SchedulerProvider;

public class CalendarDialogViewModel extends BaseViewModel<CalendarDialogCallback> {

    private final ObservableField<String> dialogText = new ObservableField<>();

    public CalendarDialogViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public ObservableField<String> getDialogText() {
        return dialogText;
    }

    public void onNoClick() {
        getNavigator().dismissDialog();
    }

    public void onYesClick() {
        getNavigator().onSubmit();
    }

    public void setText(String text){
        dialogText.set(text);
    }
}
