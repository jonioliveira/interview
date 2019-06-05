package com.jonioliveira.interview.ui.calendar;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.jonioliveira.interview.data.DataManager;
import com.jonioliveira.interview.data.model.CalendarItem;
import com.jonioliveira.interview.ui.base.BaseViewModel;
import com.jonioliveira.interview.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarViewModel extends BaseViewModel<CalendarNavigator> implements CalendarAdapter.CalendarAdapterListener {

    private Calendar calendar;

    private final MutableLiveData<List<CalendarItem>> calendarItemsLiveData;

    private ObservableField<String> date = new ObservableField<>();

    private ObservableBoolean addVisible = new ObservableBoolean();

    private List<CalendarItem> calendarItemsList = new ArrayList<>();

    public CalendarViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        calendarItemsLiveData = new MutableLiveData<>();
        addVisible.set(false);
        setHours();
    }

    private void setHours() {
        List<CalendarItem> list = new ArrayList<>();
        for(int i = 0; i < 24; i++){
            if(i<10){
                list.add(new CalendarItem(i, "0"+i+":00", false, false));
            } else{
                list.add(new CalendarItem(i, i+":00", false, false));
            }
        }

        calendarItemsLiveData.setValue(list);
    }

    public LiveData<List<CalendarItem>> getCalendarItemsLiveData() {
        return calendarItemsLiveData;
    }

    public ObservableField<String> getDate() {
        return date;
    }

    public ObservableBoolean getAddVisible() {
        return addVisible;
    }

    public void setCalendar(int year, int month, int dayOfMonth){
        calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        setDate(year, month, dayOfMonth);
    }

    public void setDate(int year, int month, int dayOfMonth){
        date.set(dayOfMonth + " / " + month + " / " + year);
    }

    public void onNavBackClick() {
        getNavigator().goBack();
    }

    @Override
    public void onItemSelected(CalendarItem calendarItem) {
        if (calendarItemsList.contains(calendarItem)) {
            calendarItemsList.remove(calendarItem);
        }else {
            calendarItemsList.add(calendarItem);
        }

        if (calendarItemsList.size() > 0) {
            addVisible.set(true);
        } else {
            addVisible.set(false);
        }
    }

    public void onFabClick(){
        getNavigator().add();
    }
}
