package com.jonioliveira.interview.utils;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.jonioliveira.interview.data.model.CalendarItem;
import com.jonioliveira.interview.ui.calendar.CalendarAdapter;

import java.util.List;

public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    @BindingAdapter({"adapter"})
    public static void addCalendarItems(RecyclerView recyclerView, List<CalendarItem> calendarItemList) {
        CalendarAdapter adapter = (CalendarAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(calendarItemList);
            adapter.notifyDataSetChanged();
        }
    }
}
