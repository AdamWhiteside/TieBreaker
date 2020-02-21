package com.mewtwo2.settlethescore.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.mewtwo2.settlethescore.ui.PickGameAdapter;

public class PickGameActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_game);

        recyclerView = findViewById(R.id.game_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PickGameAdapter();
        recyclerView.setAdapter(adapter);
    }
}
