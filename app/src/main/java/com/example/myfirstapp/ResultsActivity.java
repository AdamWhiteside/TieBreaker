package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultsActivity extends AppCompatActivity {

    TextView player_one_score_text_view;
    TextView player_two_score_text_view;
    TextView winner_text_view;
    Button new_game;
    private int playerOneScore;
    private int playerTwoScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            playerOneScore = extras.getInt("playerOneScore");
            playerTwoScore = extras.getInt("playerTwoScore");
        }

        player_one_score_text_view = (TextView) findViewById(R.id.player_one_score);
        player_one_score_text_view.append(" " + playerOneScore);

        player_two_score_text_view = (TextView) findViewById(R.id.player_two_score);
        player_two_score_text_view.append(" " + playerTwoScore);

        winner_text_view = (TextView) findViewById(R.id.winner_text);
        if (playerOneScore == playerTwoScore)
        {
            winner_text_view.setText(getString(R.string.tie_text));
        }
        if (playerOneScore > playerTwoScore) {
            winner_text_view.setText(getString(R.string.player_one_wins));
        }
        if (playerOneScore < playerTwoScore){
            winner_text_view.setText(getString(R.string.player_two_wins));
        }

        new_game = (Button) findViewById(R.id.button_new_game);
        new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Sorry, this button doesn't work yet :)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
