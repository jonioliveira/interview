package com.jonioliveira.interview.utils;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jonioliveira.interview.data.model.CalendarItem;
import com.jonioliveira.interview.data.model.api.BlogResponse;
import com.jonioliveira.interview.ui.calendar.CalendarAdapter;
import com.jonioliveira.interview.ui.calendar.CalendarItemViewModel;
import com.jonioliveira.interview.ui.feed.blogs.BlogAdapter;
import com.jonioliveira.interview.ui.feed.opensource.OpenSourceAdapter;
import com.jonioliveira.interview.ui.feed.opensource.OpenSourceItemViewModel;

import java.util.List;

public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    @BindingAdapter({"adapter"})
    public static void addBlogItems(RecyclerView recyclerView, List<BlogResponse.Blog> blogs) {
        BlogAdapter adapter = (BlogAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(blogs);
        }
    }

    @BindingAdapter({"adapter"})
    public static void addOpenSourceItems(RecyclerView recyclerView, List<OpenSourceItemViewModel> openSourceItems) {
        OpenSourceAdapter adapter = (OpenSourceAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(openSourceItems);
        }
    }

    @BindingAdapter({"adapter"})
    public static void addCalendarItems(RecyclerView recyclerView, List<CalendarItem> calendarItemList) {
        CalendarAdapter adapter = (CalendarAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(calendarItemList);
        }
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }
}
