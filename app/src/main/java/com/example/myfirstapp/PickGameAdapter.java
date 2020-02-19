package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
        gameInfoList = GameRegistry.getGameInfoList();
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
        final int p = position;

        holder.imageView.setImageResource(gameInfoList.get(p).logoID);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PopUpActivity.class);
                intent.putExtra("playerOneTurn", true);
                intent.putExtra("GameInfo",gameInfoList.get(p));
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gameInfoList.size();
    }
}
