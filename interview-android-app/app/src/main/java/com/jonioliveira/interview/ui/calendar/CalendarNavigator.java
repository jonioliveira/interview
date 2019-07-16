package com.jonioliveira.interview.ui.calendar;

import com.jonioliveira.interview.ui.calendar.dialog.CalendarItemClickType;

public interface CalendarNavigator {
    void goBack();
    void add(CalendarItemClickType type);
    void handleError(Throwable throwable);
    void slotSubmited();
    void slotSubmissionError();
    void handleCalendarRefresh();
}
