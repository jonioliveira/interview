package com.jonioliveira.interview.ui.calendar;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.databinding.ObservableField;

import com.jonioliveira.interview.R;
import com.jonioliveira.interview.data.model.SlotStatusEnum;

public class CalendarItemViewModel {
    public final ObservableField<String> content = new ObservableField<>();

    public final ObservableField<String> hourLabel = new ObservableField<>();

    public final ObservableField<Drawable> bgColor = new ObservableField<>();

    private CalendarItemViewModelListener listener;

    private Context context;

    public CalendarItemViewModel(Context context, String content, String hourLabel, CalendarItemViewModelListener listener) {
        this.content.set(content);
        this.bgColor.set(context.getDrawable(R.drawable.item_free_background));
        this.hourLabel.set(hourLabel);
        this.listener = listener;
        this.context = context;
    }

    public void onItemClick(){
        listener.onItemClick();
    }

    public void setBackground(SlotStatusEnum status, boolean isSelected){
        if (isSelected){
            bgColor.set(context.getDrawable(R.drawable.item_selected_background));
        }else {
            if(status == SlotStatusEnum.INTERVIEW){
                bgColor.set(context.getDrawable(R.drawable.item_interview_background));
            } else if (status == SlotStatusEnum.AVAILABLE){
                bgColor.set(context.getDrawable(R.drawable.item_available_background));
            } else {
                context.getDrawable(R.drawable.item_free_background);
            }
        }
    }

    public interface CalendarItemViewModelListener{
        void onItemClick();
    }
}
