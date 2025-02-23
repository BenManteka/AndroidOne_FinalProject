package com.hit.androidonefinalproject.views.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.hit.androidonefinalproject.adapter.GamesAdapter;
import com.hit.androidonefinalproject.databinding.FragmentGamesBinding;
import com.hit.androidonefinalproject.model.GameModel;
import com.hit.androidonefinalproject.model.GamesWrapperModel;
import com.hit.androidonefinalproject.utils.MySharedPreferences;
import com.hit.androidonefinalproject.views.viewmodels.GamesFragmentViewModel;

import java.util.ArrayList;

public class GamesFragment extends Fragment implements GamesAdapter.GameOnClickListener {

    private FragmentGamesBinding binding;
    private GamesFragmentViewModel viewModel;
    private GamesAdapter adapter;
    private RecyclerView.SmoothScroller smoothScroller;
    private ArrayList<GameModel> originalGames = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGamesBinding.inflate(inflater, container, false);
        viewModel = new GamesFragmentViewModel(new MySharedPreferences(this.getContext()));
        adapter = new GamesAdapter(
                new GamesWrapperModel(new ArrayList<>()),
                this::onItemClick
        );
        binding.gamesRv.setAdapter(adapter);
        binding.gamesRv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        smoothScroller = new
                LinearSmoothScroller(this.getContext()) {
                    @Override
                    protected int getVerticalSnapPreference() {
                        return LinearSmoothScroller.SNAP_TO_START;
                    }
                };
        setListeners();
        setDropDownMenu();
        viewModel.getGames();

        return binding.getRoot();
    }

    private void setDropDownMenu() {
        String[] items = new String[]{"Genre", "Developer", "Name"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        binding.spinner1.setAdapter(adapter);
    }

    private void setListeners() {
        viewModel.getData().observe(getViewLifecycleOwner(), games -> {
            originalGames = new ArrayList<>(games.getGamesList()); // שמירת הרשימה המקורית
            adapter.setAdapterData(games);
        });

        binding.searchBtn.setOnClickListener(v -> {
            search();
        });
    }

    private void search() {
        String userInput = binding.searchEt.getText().toString().toLowerCase().trim();

        if (userInput.isEmpty()) {
            // אם השדה ריק – מציגים את הרשימה המקורית
            adapter.setAdapterData(new GamesWrapperModel(new ArrayList<>(originalGames)));
            return;
        }

        ArrayList<GameModel> filteredGames = new ArrayList<>();

        // מבצע סינון מהרשימה המקורית, ולא מהרשימה המסוננת הקודמת!
        for (GameModel game : originalGames) {
            if (game.title.toLowerCase().contains(userInput) ||
                    game.genre.toLowerCase().contains(userInput) ||
                    game.developer.toLowerCase().contains(userInput) ||
                    game.publisher.toLowerCase().contains(userInput) ||
                    game.platform.toLowerCase().contains(userInput) ||
                    game.short_description.toLowerCase().contains(userInput)) {

                filteredGames.add(game);
            }
        }

        if (filteredGames.isEmpty()) {
            Toast.makeText(getContext(), "No matching games found!", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setAdapterData(new GamesWrapperModel(filteredGames));
        }
    }

    private void scrollToGame(int gamePosition) {
        smoothScroller.setTargetPosition(gamePosition);
        binding.gamesRv.getLayoutManager().startSmoothScroll(smoothScroller);
    }

    @Override
    public void onItemClick(int position) {
        ArrayList<GameModel> games = adapter.getAdapterData();
        GameModel selectedGame = games.get(position);
        String url = selectedGame.game_url;

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}