package com.jonioliveira.interview.ui.calendar;

public interface CalendarNavigator {
    void goBack();
    void add();
    void handleError(Throwable throwable);
    void slotSubmited();
    void slotSubmissionError();
    void handleCalendarRefresh();
}
