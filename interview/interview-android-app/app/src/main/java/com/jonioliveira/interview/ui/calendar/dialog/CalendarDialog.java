package com.jonioliveira.interview.ui.calendar.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.jonioliveira.interview.R;
import com.jonioliveira.interview.ViewModelProviderFactory;
import com.jonioliveira.interview.databinding.DialogCalendarBinding;
import com.jonioliveira.interview.ui.base.BaseDialog;
import com.jonioliveira.interview.utils.AppConstants;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class CalendarDialog extends BaseDialog implements CalendarDialogCallback {

    public interface Listener{
        void onDismiss();
        void onSubmit();
    }

    private static final String TAG = CalendarDialog.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private CalendarDialogViewModel mCalendarDialogViewModel;

    private Listener listener;

    public static CalendarDialog newInstance(String text) {
        CalendarDialog fragment = new CalendarDialog();
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.DIALOG_TEXT, text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void dismissDialog() {
        listener.onDismiss();
        dismissDialog(TAG);
    }

    @Override
    public void onSubmit() {
        listener.onSubmit();
        dismissDialog(TAG);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DialogCalendarBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_calendar, container, false);
        View view = binding.getRoot();

        AndroidSupportInjection.inject(this);
        mCalendarDialogViewModel = ViewModelProviders.of(this,factory).get(CalendarDialogViewModel.class);
        binding.setViewModel(mCalendarDialogViewModel);

        mCalendarDialogViewModel.setNavigator(this);
        mCalendarDialogViewModel.setText(Objects.requireNonNull(getArguments()).getString(AppConstants.DIALOG_TEXT));

        return view;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
}
