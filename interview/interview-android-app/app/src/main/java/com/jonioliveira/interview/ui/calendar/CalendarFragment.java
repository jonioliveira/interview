package com.jonioliveira.interview.ui.calendar;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jonioliveira.interview.BR;
import com.jonioliveira.interview.R;
import com.jonioliveira.interview.ViewModelProviderFactory;
import com.jonioliveira.interview.databinding.FragmentCalendarBinding;
import com.jonioliveira.interview.ui.base.BaseFragment;
import com.jonioliveira.interview.ui.calendar.rating.RateUsDialog;
import com.jonioliveira.interview.utils.AppConstants;

import java.util.Calendar;

import javax.inject.Inject;

public class CalendarFragment extends BaseFragment<FragmentCalendarBinding, CalendarViewModel> implements CalendarNavigator {

    public static final String TAG = CalendarFragment.class.getSimpleName();

    @Inject
    CalendarAdapter calendarAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    FragmentCalendarBinding fragmentCalendarBinding;

    @Inject
    ViewModelProviderFactory factory;

    private CalendarViewModel mCalendarViewModel;

    public static CalendarFragment newInstance(Calendar calendar){
        Bundle args = new Bundle();
        args.putInt(AppConstants.YEAR, calendar.get(Calendar.YEAR));
        args.putInt(AppConstants.MONTH, calendar.get(Calendar.MONTH));
        args.putInt(AppConstants.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
        CalendarFragment fragment = new CalendarFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_calendar;
    }

    @Override
    public CalendarViewModel getViewModel() {
        mCalendarViewModel = ViewModelProviders.of(this, factory).get(CalendarViewModel.class);
        return mCalendarViewModel;
    }

    @Override
    public void goBack() {
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void add() {
        RateUsDialog.newInstance().show(getFragmentManager());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCalendarViewModel.setNavigator(this);
        mCalendarViewModel.setCalendar(getArguments().getInt(AppConstants.YEAR), getArguments().getInt(AppConstants.MONTH), getArguments().getInt(AppConstants.DAY_OF_MONTH));
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentCalendarBinding = getViewDataBinding();
        setUp();
    }

    private void setUp() {
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        fragmentCalendarBinding.calendarRecyclerView.setLayoutManager(mLayoutManager);
        fragmentCalendarBinding.calendarRecyclerView.setItemAnimator(new DefaultItemAnimator());
        fragmentCalendarBinding.calendarRecyclerView.setAdapter(calendarAdapter);
        calendarAdapter.setListener(mCalendarViewModel);
    }
}