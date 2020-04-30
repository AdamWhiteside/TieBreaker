package com.mewtwo2.settlethescore.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.mewtwo2.settlethescore.registration.GameInfo;

public class PopUpActivity extends GameActivity {

    private boolean playerOneTurn;
    private int playerOneScore;
    private GameInfo gameToLaunch;
    private String player_one_choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            playerOneTurn = extras.getBoolean("playerOneTurn");
            playerOneScore = extras.getInt("playerOneScore");
            gameToLaunch = (GameInfo)extras.getSerializable("GameInfo");
            player_one_choice = extras.getString("player_one_choice");
        }

        setContentView(R.layout.activity_pop_up);

        //Set Game Logo ImageView
        ImageView imageGameLogo = findViewById(R.id.imageGameLogo);
        imageGameLogo.setImageResource(gameToLaunch.logoID);

        //Set Game Name TextView
        TextView textGameName = findViewById(R.id.textGameName);
        textGameName.setText(gameToLaunch.nameID);

        //set instruction text view
        TextView textGameInstructions = findViewById(R.id.textGameInstructions);
        textGameInstructions.setMovementMethod(new ScrollingMovementMethod());
        textGameInstructions.setText(gameToLaunch.instructionsID);


        TextView textPlayerNumber = findViewById(R.id.textPlayerNumber);

        //if game is interactive, hide player text view
        if (gameToLaunch.gameType == GameInfo.GameType.Interactive)
        {
            textPlayerNumber.setText(R.string.player_interactive);
        }
        //display player text view
        else {
            if (playerOneTurn == true) {
                getSupportActionBar().setTitle(R.string.player_one);
                textPlayerNumber.setText(R.string.player_one);
            }
            else {
                getSupportActionBar().setTitle(R.string.player_two);
                textPlayerNumber.setText(R.string.player_two);
            }
        }

        Button buttonReady = findViewById(R.id.buttonReady);
        buttonReady.setOnClickListener(new View.OnClickListener() {
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
        intent.putExtra("player_one_choice", player_one_choice);
        startActivity(intent);
    }
}
