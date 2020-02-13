package com.example.myfirstapp;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mewtwo2.settlethescore.registration.GameInfo;
import com.mewtwo2.settlethescore.registration.GameRegistry;

import java.util.List;

public class PickGameAdapter extends RecyclerView.Adapter<PickGameAdapter.GameViewHolder> {
    private List<GameInfo> gameInfoList;

    public static class GameViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public GameViewHolder(ImageView v) {
            super(v);
            imageView = v;
        }
    }

    public PickGameAdapter() {
        gameInfoList = GameRegistry.getRegistrationList();
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Create new View
        ImageView v = new ImageView(parent.getContext());
        v.setAdjustViewBounds(true);
        return new GameViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        holder.imageView.setImageResource(gameInfoList.get(position).logoID);
    }

    @Override
    public int getItemCount() {
        return gameInfoList.size();
    }
}
