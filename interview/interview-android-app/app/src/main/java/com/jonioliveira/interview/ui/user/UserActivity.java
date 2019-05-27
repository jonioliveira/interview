package com.jonioliveira.interview.ui.user;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jonioliveira.interview.BR;
import com.jonioliveira.interview.R;
import com.jonioliveira.interview.ViewModelProviderFactory;
import com.jonioliveira.interview.databinding.ActivityUserBinding;
import com.jonioliveira.interview.ui.base.BaseActivity;
import com.jonioliveira.interview.ui.main.MainActivity;
import com.jonioliveira.interview.utils.AppLogger;

import javax.inject.Inject;

public class UserActivity extends BaseActivity<ActivityUserBinding, UserViewModel> implements UserNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private UserViewModel mUserViewModel;
    private ActivityUserBinding mActivityUserBinding;

    public static Intent newIntent(Context context){
        return new Intent(context, UserActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    public UserViewModel getViewModel() {
        mUserViewModel = ViewModelProviders.of(this, factory).get(UserViewModel.class);
        return mUserViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityUserBinding = getViewDataBinding();
        mUserViewModel.setNavigator(this);
    }

    @Override
    public void openLoginActivity() {
        Intent intent = MainActivity.newIntent(UserActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void onAddUserClick() {
        String name = mActivityUserBinding.etName.getText().toString();
        AppLogger.d(name);
        mUserViewModel.onAddUserClick();
    }
}
