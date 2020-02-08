package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PopUpActivity extends AppCompatActivity {

    private Button ready_btn;
    private TextView player_text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);
        player_text_view = (TextView) findViewById(R.id.player_text);
        if (CoinFlipActivity.playerOneTurn == true) {
            player_text_view.setText(R.string.player_one);
        }
        else {
            player_text_view.setText(R.string.player_two);
        }
        ready_btn = (Button) findViewById(R.id.button_ready);
        ready_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerReady();
            }
        });
    }

    private void playerReady()
    {
        openCoinFlipActivity();
    }

    public void openCoinFlipActivity() {
        Intent intent = new Intent(this,CoinFlipActivity.class);
        startActivity(intent);
    }
}
