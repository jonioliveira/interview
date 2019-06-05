package com.jonioliveira.interview.ui.calendar;

import android.graphics.drawable.Drawable;

import androidx.databinding.ObservableField;

public class CalendarItemViewModel {
    public final ObservableField<String> content = new ObservableField<>();

    public final ObservableField<String> hour = new ObservableField<>();

    public final ObservableField<Drawable> bgColor = new ObservableField<>();

    public CalendarItemViewModelListener listener;

    public CalendarItemViewModel(String content, String hour, Drawable bgColor, CalendarItemViewModelListener listener) {
        this.content.set(content);
        this.bgColor.set(bgColor);
        this.hour.set(hour);
        this.listener = listener;
    }

    public void onItemClick(){
        listener.onItemClick();
    }

    public interface CalendarItemViewModelListener{
        void onItemClick();
    }
}
