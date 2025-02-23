package com.hit.androidonefinalproject.views.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hit.androidonefinalproject.model.GamesWrapperModel;
import com.hit.androidonefinalproject.repository.NetworkRepository;
import com.hit.androidonefinalproject.utils.IMySharedPreferences;
import com.hit.androidonefinalproject.utils.MySharedPreferences;


public class GamesFragmentViewModel extends ViewModel {

    NetworkRepository repo;
    MySharedPreferences preferences;
    MutableLiveData<GamesWrapperModel> data = new MutableLiveData<>();

    public GamesFragmentViewModel(IMySharedPreferences preferences) {
        repo = new NetworkRepository();
        this.preferences = (MySharedPreferences) preferences;
    }

    public void getGames() {
        if(!preferences.isDataStored()) {
            repo.getGames();
            repo.getAppData().observeForever(games -> {
                GamesWrapperModel wrapper = new GamesWrapperModel(games);
                preferences.setGamesDataToLocalStorage(wrapper);
                data.postValue(wrapper);
            });
        }
        else {
            data.postValue(preferences.getGamesDataFromLocalStorage());
        }
    }

    public LiveData<GamesWrapperModel> getData() {
        return data;
    }

}
