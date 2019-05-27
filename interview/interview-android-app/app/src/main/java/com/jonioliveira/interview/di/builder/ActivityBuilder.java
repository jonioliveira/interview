package com.jonioliveira.interview.di.builder;

import com.jonioliveira.interview.ui.about.AboutFragmentProvider;
import com.jonioliveira.interview.ui.feed.FeedActivity;
import com.jonioliveira.interview.ui.feed.FeedActivityModule;
import com.jonioliveira.interview.ui.feed.blogs.BlogFragmentProvider;
import com.jonioliveira.interview.ui.feed.opensource.OpenSourceFragmentProvider;
import com.jonioliveira.interview.ui.login.LoginActivity;
import com.jonioliveira.interview.ui.main.MainActivity;
import com.jonioliveira.interview.ui.main.rating.RateUsDialogProvider;
import com.jonioliveira.interview.ui.user.UserActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {
            FeedActivityModule.class,
            BlogFragmentProvider.class,
            OpenSourceFragmentProvider.class})
    abstract FeedActivity bindFeedActivity();

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector
    abstract UserActivity bindUserActivity();

    @ContributesAndroidInjector(modules = {
            AboutFragmentProvider.class,
            RateUsDialogProvider.class})
    abstract MainActivity bindMainActivity();

}
