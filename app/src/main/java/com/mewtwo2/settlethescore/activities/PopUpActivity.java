package com.mewtwo2.settlethescore.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.mewtwo2.settlethescore.registration.GameInfo;

public class PopUpActivity extends AppCompatActivity {

    private Button ready_btn;
    private TextView player_text_view;
    private TextView instructions;
    private boolean playerOneTurn;
    private int playerOneScore;
    private GameInfo gameToLaunch;
    private GameInfo type;
    private String player_one_choice;
    private String player_two_choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);
        instructions = (TextView) findViewById(R.id.instruction_text);
        instructions.setMovementMethod(new ScrollingMovementMethod());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            playerOneTurn = extras.getBoolean("playerOneTurn");
            playerOneScore = extras.getInt("playerOneScore");
            gameToLaunch = (GameInfo)extras.getSerializable("GameInfo");
            player_one_choice = extras.getString("player_one_choice");
            player_two_choice = extras.getString("player_two_choice");
        }

        //set action bar text to game being played
        getSupportActionBar().setTitle(gameToLaunch.nameID);
        //set instruction text view
        instructions.setText(gameToLaunch.instructionsID);

        player_text_view = (TextView) findViewById(R.id.player_text);

        //if game is interactive, hide player text view
        if (gameToLaunch.gameType == GameInfo.GameType.Interactive)
        {
            player_text_view.setVisibility(View.INVISIBLE);
        }
        //display player text view
        else {
            if (playerOneTurn == true) {
                player_text_view.setText(R.string.player_one);
            }
            else {
                player_text_view.setText(R.string.player_two);
            }
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
        openGameActivity();
    }

    public void openGameActivity() {
        Intent intent = new Intent(this, gameToLaunch.activity);
        intent.putExtra("playerOneTurn", playerOneTurn);
        intent.putExtra("playerOneScore", playerOneScore);
        startActivity(intent);
    }
}
