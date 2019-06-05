package com.jonioliveira.interview;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.jonioliveira.interview.data.DataManager;
import com.jonioliveira.interview.ui.about.AboutViewModel;
import com.jonioliveira.interview.ui.calendar.CalendarViewModel;
import com.jonioliveira.interview.ui.feed.FeedViewModel;
import com.jonioliveira.interview.ui.feed.blogs.BlogViewModel;
import com.jonioliveira.interview.ui.feed.opensource.OpenSourceViewModel;
import com.jonioliveira.interview.ui.login.LoginViewModel;
import com.jonioliveira.interview.ui.main.MainViewModel;
import com.jonioliveira.interview.ui.calendar.rating.RateUsViewModel;
import com.jonioliveira.interview.ui.user.UserViewModel;
import com.jonioliveira.interview.utils.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

  private final DataManager dataManager;
  private final SchedulerProvider schedulerProvider;

  @Inject
  public ViewModelProviderFactory(DataManager dataManager,
      SchedulerProvider schedulerProvider) {
    this.dataManager = dataManager;
    this.schedulerProvider = schedulerProvider;
  }


  @Override
  public <T extends ViewModel> T create(Class<T> modelClass) {
    if (modelClass.isAssignableFrom(AboutViewModel.class)) {
      //noinspection unchecked
      return (T) new AboutViewModel(dataManager,schedulerProvider);
    } else if (modelClass.isAssignableFrom(FeedViewModel.class)) {
      //noinspection unchecked
      return (T) new FeedViewModel(dataManager,schedulerProvider);
    } else if (modelClass.isAssignableFrom(LoginViewModel.class)) {
      //noinspection unchecked
      return (T) new LoginViewModel(dataManager,schedulerProvider);
    } else if (modelClass.isAssignableFrom(MainViewModel.class)) {
      //noinspection unchecked
      return (T) new MainViewModel(dataManager,schedulerProvider);
    }
    else if (modelClass.isAssignableFrom(BlogViewModel.class)) {
      //noinspection unchecked
      return (T) new BlogViewModel(dataManager,schedulerProvider);
    }
    else if (modelClass.isAssignableFrom(RateUsViewModel.class)) {
      //noinspection unchecked
      return (T) new RateUsViewModel(dataManager,schedulerProvider);
    }
    else if (modelClass.isAssignableFrom(OpenSourceViewModel.class)) {
      //noinspection unchecked
      return (T) new OpenSourceViewModel(dataManager,schedulerProvider);
    }
    else if (modelClass.isAssignableFrom(UserViewModel.class)) {
      //noinspection unchecked
      return (T) new UserViewModel(dataManager,schedulerProvider);
    }
    else if (modelClass.isAssignableFrom(CalendarViewModel.class)){
      //noinspection unchecked
      return (T) new CalendarViewModel(dataManager, schedulerProvider);
    }
    throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
  }
}