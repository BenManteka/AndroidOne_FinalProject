package com.hit.androidonefinalproject.repository;

import androidx.lifecycle.MutableLiveData;

import com.hit.androidonefinalproject.model.GameModel;

import java.util.ArrayList;

public interface INetworkRepository {
    MutableLiveData<ArrayList<GameModel>> getGames();
}
