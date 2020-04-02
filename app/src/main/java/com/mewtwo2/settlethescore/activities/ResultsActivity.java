package com.mewtwo2.settlethescore.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mewtwo2.settlethescore.registration.GameInfo;
import com.mewtwo2.settlethescore.registration.GameRegistry;

public class ResultsActivity extends AppCompatActivity {

    TextView player_one_score_text_view;
    TextView player_two_score_text_view;
    TextView winner_text_view;
    Button new_game;
    private int playerOneScore;
    private int playerTwoScore;
    private String player_one_choice;
    private String player_two_choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            playerOneScore = extras.getInt("playerOneScore");
            playerTwoScore = extras.getInt("playerTwoScore");
            player_one_choice = extras.getString("player_one_choice");
            player_two_choice = extras.getString("player_two_choice");
        }

        if (player_one_choice.equals(player_two_choice))
        {
            playerOneScore = playerTwoScore = 0;
        }
        else if ((player_one_choice.equals("rock") && player_two_choice.equals("paper")) || (player_one_choice.equals("scissors") && player_two_choice.equals("rock")) || (player_one_choice.equals("paper") && player_two_choice.equals("scissors")))
        {
            playerTwoScore = 1;
        }
        else
        {
            playerTwoScore = 1;
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
                openPopUpActivity(GameRegistry.getRandomGameInfo());
                //Toast.makeText(getApplicationContext(), "Sorry, this button doesn't work yet :)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openPopUpActivity(GameInfo gameToLaunch) {
        Intent intent = new Intent(this,PopUpActivity.class);
        intent.putExtra("playerOneTurn",true);
        intent.putExtra("GameInfo",gameToLaunch);
        startActivity(intent);
    }
}
