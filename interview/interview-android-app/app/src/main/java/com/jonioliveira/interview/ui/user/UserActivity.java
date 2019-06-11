package com.jonioliveira.interview.ui.user;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.jonioliveira.interview.BR;
import com.jonioliveira.interview.R;
import com.jonioliveira.interview.ViewModelProviderFactory;
import com.jonioliveira.interview.databinding.ActivityUserBinding;
import com.jonioliveira.interview.ui.base.BaseActivity;
import com.jonioliveira.interview.ui.main.MainActivity;
import com.jonioliveira.interview.utils.AppLogger;

import java.util.Objects;

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
        Toast.makeText(this, "User Added", Toast.LENGTH_SHORT).show();
        this.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onAddUserClick() {
        if (isNetworkConnected()){
            String name = Objects.requireNonNull(mActivityUserBinding.etName.getText()).toString().trim();
            int checkedRadioButtonId = mActivityUserBinding.rgUserType.getCheckedRadioButtonId();
            int userType =  (checkedRadioButtonId == mActivityUserBinding.first.getId()) ? 1 : 2;
            if (!name.isEmpty()){
                mUserViewModel.addUser(name, userType);
            } else {
                Toast.makeText(this, "Add the user name please", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void handleError(Throwable throwable) {
        if(throwable instanceof ANError){
            ANError anError = (ANError) throwable;
            if (anError.getErrorCode() == 404) {
                Toast.makeText(this, "Error on form", Toast.LENGTH_SHORT).show();
            } else if (anError.getErrorCode() == 409){
                Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error while add user", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
