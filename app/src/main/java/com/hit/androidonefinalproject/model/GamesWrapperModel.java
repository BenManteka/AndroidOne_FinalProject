package com.hit.androidonefinalproject.model;

import java.util.ArrayList;

public class GamesWrapperModel {

    ArrayList<GameModel> games;

    public GamesWrapperModel(ArrayList<GameModel> games) {
        this.games = games;
    }

    public ArrayList<GameModel> getGamesList() {
        return games;
    }

    public void setGamesList(ArrayList<GameModel> games) {
        this.games = games;
    }

}
