package com.jonioliveira.interview.ui.calendar.rating;

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
import com.jonioliveira.interview.databinding.DialogRateUsBinding;
import com.jonioliveira.interview.ui.base.BaseDialog;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class RateUsDialog extends BaseDialog implements RateUsCallback {

    private static final String TAG = RateUsDialog.class.getSimpleName();
    @Inject
    ViewModelProviderFactory factory;
    private RateUsViewModel mRateUsViewModel;

    public static RateUsDialog newInstance() {
        RateUsDialog fragment = new RateUsDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void dismissDialog() {
        dismissDialog(TAG);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DialogRateUsBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_rate_us, container, false);
        View view = binding.getRoot();

        AndroidSupportInjection.inject(this);
        mRateUsViewModel = ViewModelProviders.of(this,factory).get(RateUsViewModel.class);
        binding.setViewModel(mRateUsViewModel);

        mRateUsViewModel.setNavigator(this);

        return view;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }
}
