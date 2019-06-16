package com.jonioliveira.interview.ui.login;

import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.jonioliveira.interview.BR;
import com.jonioliveira.interview.R;
import com.jonioliveira.interview.ViewModelProviderFactory;
import com.jonioliveira.interview.databinding.ActivityLoginBinding;
import com.jonioliveira.interview.ui.base.BaseActivity;
import com.jonioliveira.interview.ui.main.MainActivity;
import com.jonioliveira.interview.ui.user.UserActivity;
import com.jonioliveira.interview.utils.AppLogger;

import java.util.Objects;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private LoginViewModel mLoginViewModel;
    private ActivityLoginBinding mActivityLoginBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        mLoginViewModel = ViewModelProviders.of(this,factory).get(LoginViewModel.class);
        return mLoginViewModel;
    }

    @Override
    public void handleError(Throwable throwable) {
        if(throwable instanceof ANError){
            ANError anError = (ANError) throwable;
            if (anError.getErrorCode() == 404) {
                Toast.makeText(this, "User does not exists", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error while login", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void login() {
        String name = Objects.requireNonNull(mActivityLoginBinding.etName.getText()).toString().trim();
        hideKeyboard();
        if (isNetworkConnected()) {
            if (!name.isEmpty()) {
                mLoginViewModel.login(name);
            } else {
                Toast.makeText(this, "Add the name of user", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Internet not connected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.newIntent(LoginActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openAddUser() {
        Intent intent = UserActivity.newIntent(LoginActivity.this);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding = getViewDataBinding();
        mLoginViewModel.setNavigator(this);
    }
}
