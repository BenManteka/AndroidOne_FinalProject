package com.hit.androidonefinalproject.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hit.androidonefinalproject.api.GamesApi;
import com.hit.androidonefinalproject.api.MyRetrofit;
import com.hit.androidonefinalproject.model.GameModel;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NetworkRepository implements INetworkRepository {

    Retrofit retrofit;
    MutableLiveData<ArrayList<GameModel>> appData = new MutableLiveData<>();

    public NetworkRepository() {
        retrofit = MyRetrofit.provideRetrofitInstance();
    }

    @Override
    public MutableLiveData<ArrayList<GameModel>> getGames() {
        GamesApi api = retrofit.create(GamesApi.class);

        Call<ArrayList<GameModel>> data = api.getGames();

        data.enqueue(new Callback<ArrayList<GameModel>>() {

            @Override
            public void onResponse(Call<ArrayList<GameModel>> call, Response<ArrayList<GameModel>> response) {
                if(response.code() == 200) {
                    appData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GameModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return null;
    }

    public LiveData<ArrayList<GameModel>> getAppData() {
        return appData;
    }
}
