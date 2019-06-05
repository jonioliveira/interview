package com.jonioliveira.interview.ui.calendar;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jonioliveira.interview.R;
import com.jonioliveira.interview.data.model.CalendarItem;
import com.jonioliveira.interview.databinding.ItemCalendarViewBinding;
import com.jonioliveira.interview.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    List<CalendarItem> list = new ArrayList<>();
    CalendarAdapterListener listener;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCalendarViewBinding itemCalendarViewBinding = ItemCalendarViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new DayItemViewHolder(itemCalendarViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return 24;
    }

    public void addItems(List<CalendarItem> repoList) {
        list.addAll(repoList);
        notifyDataSetChanged();
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
            if (calendarItem.isSelected()){
                final CalendarItemViewModel itemViewModel = new CalendarItemViewModel("FREE",
                        calendarItem.getHour(),
                        viewBinding.getRoot().getContext().getDrawable(R.drawable.item_selected_background),
                        this);
                viewModel= itemViewModel;
            }else {
                final CalendarItemViewModel itemViewModel = new CalendarItemViewModel("FREE",
                        calendarItem.getHour(),
                        viewBinding.getRoot().getContext().getDrawable(R.drawable.item_available_background),
                        this);
                viewModel= itemViewModel;
            }


            viewBinding.setViewModel(viewModel);
            viewBinding.executePendingBindings();
        }

        @Override
        public void onItemClick() {
            listener.onItemSelected(calendarItem);
            if(calendarItem.isSelected()){
                calendarItem.setSelected(false);
                viewModel.bgColor.set(viewBinding.getRoot().getContext().getDrawable(R.drawable.item_available_background));
            }else {
                calendarItem.setSelected(true);
                viewModel.bgColor.set(viewBinding.getRoot().getContext().getDrawable(R.drawable.item_selected_background));
            }
        }
    }
}
