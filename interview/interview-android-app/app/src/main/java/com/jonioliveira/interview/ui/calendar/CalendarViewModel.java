package com.jonioliveira.interview.ui.calendar;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonArray;
import com.jonioliveira.interview.data.DataManager;
import com.jonioliveira.interview.data.model.CalendarItem;
import com.jonioliveira.interview.data.model.SlotStatusEnum;
import com.jonioliveira.interview.data.model.UserTypeEnum;
import com.jonioliveira.interview.data.model.api.AddSlotRequest;
import com.jonioliveira.interview.data.model.api.SlotsForDayAndUserRequest;
import com.jonioliveira.interview.data.model.api.SlotsResponse;
import com.jonioliveira.interview.ui.base.BaseViewModel;
import com.jonioliveira.interview.utils.CollectionUtils;
import com.jonioliveira.interview.utils.TimeUtils;
import com.jonioliveira.interview.utils.rx.SchedulerProvider;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class CalendarViewModel extends BaseViewModel<CalendarNavigator> implements CalendarAdapter.CalendarAdapterListener {

    private Calendar calendar;

    private final MutableLiveData<List<CalendarItem>> calendarItemsLiveData;

    private ObservableField<String> date = new ObservableField<>();

    private CalendarItem calendarItem = null;

    public CalendarViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        calendarItemsLiveData = new MutableLiveData<>();
    }

    public void setHours() {
        setIsLoading(true);
        List<CalendarItem> list = getCalendarItemList();
        calendarItemsLiveData.setValue(list);

        getCompositeDisposable().add(getDataManager()
                .doGetSlotsForDayByUser(new SlotsForDayAndUserRequest(calendar.getTime(), getDataManager().getCurrentUserId()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    for (SlotsResponse slotsResponse : response) {
                        CalendarItem calendarItem = CollectionUtils.find(list, item -> TimeUtils.dateIsSame(item.getStartDate(), slotsResponse.getStartDate()));
                        if (calendarItem != null) {
                            calendarItem.setSlotId(slotsResponse.getId());
                            calendarItem.setStatus(SlotStatusEnum.fromValue(slotsResponse.getStatus()));
                        }
                    }
                    setIsLoading(false);
                    getNavigator().handleCalendarRefresh();
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public LiveData<List<CalendarItem>> getCalendarItemsLiveData() {
        return calendarItemsLiveData;
    }

    public ObservableField<String> getDate() {
        return date;
    }

    public void setCalendar(int year, int month, int dayOfMonth) {
        calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        setDate(year, month, dayOfMonth);
        setHours();
    }

    public void setDate(int year, int month, int dayOfMonth) {
        date.set(dayOfMonth + " / " + (month + 1) + " / " + year);
    }

    public void onNavBackClick() {
        getNavigator().goBack();
    }

    @Override
    public void onItemSelected(CalendarItem calendarItem) {
        this.calendarItem = calendarItem;
        getNavigator().add();
    }

    public void submit(){
        UserTypeEnum userType = UserTypeEnum.fromValue(getDataManager().getCurrentUserTypeId());

        AddSlotRequest[] addSlotRequests = new AddSlotRequest[]{new AddSlotRequest(calendarItem.getStartDate(), calendarItem.getEndDate(), getDataManager().getCurrentUserId())};
        if(userType == UserTypeEnum.INTERVIEWER){
            getCompositeDisposable().add(getDataManager()
                    .doAddSlotRequest(addSlotRequests)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(response -> {
                        getNavigator().slotSubmited();
                        setIsLoading(false);
                    }, throwable -> {
                        setIsLoading(false);
                        getNavigator().slotSubmissionError();
                    }));
        }else {

        }
    }

    public void refresh(){
        calendarItem.setSelected(false);
        getNavigator().handleCalendarRefresh();
    }

    private List<CalendarItem> getCalendarItemList(){
        List<CalendarItem> list = new ArrayList<>();
        Calendar start = Calendar.getInstance();
        start.setTime(calendar.getTime());
        start.set(Calendar.HOUR_OF_DAY, 8);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);

        Calendar end = Calendar.getInstance();
        end.setTime(calendar.getTime());
        end.set(Calendar.HOUR_OF_DAY, 9);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);

        for (int i = 0; i < 13 ; i++){
            list.add(new CalendarItem(i, start.getTime(), end.getTime()));
            start.add(Calendar.HOUR_OF_DAY, 1);
            end.add(Calendar.HOUR_OF_DAY, 1);
        }
        return list;
    }
}
