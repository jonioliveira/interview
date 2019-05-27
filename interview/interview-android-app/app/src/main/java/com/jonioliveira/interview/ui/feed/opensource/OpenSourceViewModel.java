package com.jonioliveira.interview.ui.feed.opensource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.jonioliveira.interview.data.DataManager;
import com.jonioliveira.interview.data.model.api.OpenSourceResponse;
import com.jonioliveira.interview.ui.base.BaseViewModel;
import com.jonioliveira.interview.utils.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public class OpenSourceViewModel extends BaseViewModel<OpenSourceNavigator> {

    private final MutableLiveData<List<OpenSourceItemViewModel>> openSourceItemsLiveData;

    public OpenSourceViewModel(DataManager dataManager,
                               SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        openSourceItemsLiveData = new MutableLiveData<>();
        fetchRepos();
    }

    public void fetchRepos() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getOpenSourceApiCall()
                .map(openSourceResponse -> openSourceResponse.getData())
                .flatMap(this::getViewModelList)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(openSourceResponse -> {
                    openSourceItemsLiveData.setValue(openSourceResponse);
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public LiveData<List<OpenSourceItemViewModel>> getOpenSourceItemsLiveData() {
        return openSourceItemsLiveData;
    }

    private Single<List<OpenSourceItemViewModel>> getViewModelList(List<OpenSourceResponse.Repo> repoList) {
        return Observable.fromIterable(repoList)
                .map(repo -> new OpenSourceItemViewModel(
                        repo.getCoverImgUrl(), repo.getTitle(),
                        repo.getDescription(), repo.getProjectUrl())).toList();
    }
}
