package com.jonioliveira.interview.ui.calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jonioliveira.interview.data.model.CalendarItem;
import com.jonioliveira.interview.data.model.SlotStatusEnum;
import com.jonioliveira.interview.databinding.ItemCalendarViewBinding;
import com.jonioliveira.interview.databinding.ItemEmptyCalendarViewBinding;
import com.jonioliveira.interview.ui.base.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CalendarAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    List<CalendarItem> list = new ArrayList<>();
    CalendarAdapterListener listener;
    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case VIEW_TYPE_NORMAL:
            ItemCalendarViewBinding itemCalendarViewBinding = ItemCalendarViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new DayItemViewHolder(itemCalendarViewBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemEmptyCalendarViewBinding itemEmptyCalendarViewBinding = ItemEmptyCalendarViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new EmptyItemViewHolder(itemEmptyCalendarViewBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (!list.isEmpty()){
            return list.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (!list.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    public void addItems(List<CalendarItem> repoList) {
        list.addAll(repoList);
    }

    public void clearItems() {
        list.clear();
    }

    public void setListener(CalendarAdapterListener listener){
        this.listener = listener;
    }

    public interface CalendarAdapterListener{
        void onItemSelected(CalendarItem calendarItem);
    }

    public class EmptyItemViewHolder extends BaseViewHolder{

        private ItemEmptyCalendarViewBinding viewBinding;

        public EmptyItemViewHolder(ItemEmptyCalendarViewBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
        }

        @Override
        public void onBind(int position) {
            viewBinding.executePendingBindings();
        }
    }

    public class DayItemViewHolder extends BaseViewHolder implements CalendarItemViewModel.CalendarItemViewModelListener {

        private ItemCalendarViewBinding viewBinding;

        private CalendarItemViewModel viewModel;

        private CalendarItem calendarItem;


        public DayItemViewHolder(ItemCalendarViewBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
        }

        @Override
        public void onBind(int position) {
            calendarItem = list.get(position);
            String text = calendarItem.getStatus().toString();

            SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());
            viewModel = new CalendarItemViewModel(viewBinding.getRoot().getContext(), text, format.format(calendarItem.getStartDate()), this);
            viewModel.setBackground(calendarItem.getStatus(), calendarItem.isSelected());

            viewBinding.setViewModel(viewModel);
            viewBinding.executePendingBindings();
        }

        @Override
        public void onItemClick() {
            if(calendarItem.getStatus() != SlotStatusEnum.INTERVIEW){
                listener.onItemSelected(calendarItem);
                if(calendarItem.isSelected()){
                    calendarItem.setSelected(false);
                    viewModel.setBackground(calendarItem.getStatus(), false);
                }
                calendarItem.setSelected(true);
                viewModel.setBackground(calendarItem.getStatus(), true);
            }
        }
    }
}
