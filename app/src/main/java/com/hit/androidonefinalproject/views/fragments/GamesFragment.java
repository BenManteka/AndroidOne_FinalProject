package com.hit.androidonefinalproject.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hit.androidonefinalproject.adapter.GamesAdapter;
import com.hit.androidonefinalproject.databinding.FragmentGamesBinding;
import com.hit.androidonefinalproject.model.FilteredGameModel;
import com.hit.androidonefinalproject.model.UnfilteredGameModel;
import com.hit.androidonefinalproject.views.viewmodels.GamesFragmentViewModel;

import java.util.ArrayList;

public class GamesFragment extends Fragment {

    private FragmentGamesBinding binding;
    private GamesFragmentViewModel viewModel;
    private GamesAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGamesBinding.inflate(inflater, container, false);
        viewModel = new GamesFragmentViewModel();
        adapter = new GamesAdapter(new FilteredGameModel(new ArrayList<>()));
        binding.gamesRv.setAdapter(adapter);
        binding.gamesRv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        setListeners();

        viewModel.getGames();


        return binding.getRoot();
    }

    private void setListeners() {
        viewModel.data.observe(getViewLifecycleOwner(), games -> {
            adapter.setAdapterData(games);
        });
    }
}
