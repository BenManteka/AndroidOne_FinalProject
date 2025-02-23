package com.hit.androidonefinalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hit.androidonefinalproject.databinding.ListItemBinding;
import com.hit.androidonefinalproject.model.GamesWrapperModel;
import com.hit.androidonefinalproject.model.GameModel;

import java.util.ArrayList;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {

    ArrayList<GameModel> games;
    private GameOnClickListener itemClickListener;

    public GamesAdapter(GamesWrapperModel wrapper, GameOnClickListener listener) {
        this.games = wrapper.getGamesList();
        itemClickListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        ListItemBinding binding = ListItemBinding.inflate(inflater,parent,false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(games.get(position));
        holder.binding.gameUrlTv.setOnClickListener( v ->
                itemClickListener.onItemClick(position)
        );
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public void setAdapterData(GamesWrapperModel games) {
        this.games = games.getGamesList();
        notifyDataSetChanged();
    }

    public ArrayList<GameModel> getAdapterData() {
         return games;
    }

    public interface GameOnClickListener {
        void onItemClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ListItemBinding binding;

        public ViewHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(GameModel game) {
            binding.title.setText(game.title);
            Glide.with(this.itemView.getContext())
                    .load(game.thumbnail)
                    .into(binding.gameImage);
            binding.genre.setText(game.genre);
            binding.description.setText(game.short_description);
            binding.platform.setText(game.platform);
            binding.publisher.setText(game.publisher);
            binding.developer.setText(game.developer);
            binding.releaseDate.setText(game.release_date);
        }

    }

}
