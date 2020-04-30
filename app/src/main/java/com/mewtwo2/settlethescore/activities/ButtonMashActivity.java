package com.mewtwo2.settlethescore.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mewtwo2.settlethescore.registration.GameInfo;
import com.mewtwo2.settlethescore.registration.GameRegistry;

public class ButtonMashActivity extends GameActivity{
    ImageView arcadeButton;
    TextView result, info;

    int currentTaps = 0;
    boolean gameStarted = false;

    CountDownTimer timer;

    int bestResult = 0;
    private Handler handler = new Handler();
    int playerOneScore = 0;
    int playerTwoScore = 0;
    boolean playerOneTurn = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttonmash);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            playerOneTurn = extras.getBoolean("playerOneTurn");
            playerOneScore = extras.getInt("playerOneScore");
        }

        arcadeButton = findViewById(R.id.iv_tap);
        result = findViewById(R.id.tv_result);

        final SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        bestResult = preferences.getInt("highScore", 0);

        result.setText("Best Result:" + bestResult);

        arcadeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gameStarted)
                    currentTaps++;
                else{
                    gameStarted = true;
                    timer.start();
                    currentTaps++;
                }
            }
        });

            timer = new CountDownTimer(5000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long timeTillEnd = (millisUntilFinished / 1000) + 1;
                    result.setText("Time Remaining: " + timeTillEnd);
                }
                @Override
                public void onFinish() {
                    arcadeButton.setEnabled(false);
                    gameStarted = false;
                    result.setText("Current Taps: " + currentTaps);
                    if(playerOneTurn) {
                        playerOneScore = currentTaps;
                        playerOneTurn = false;

                        //delay for 2 seconds
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                openPopUpActivity();
                            }
                        }, 2000);
                    }
                    else
                    {
                        playerTwoScore = currentTaps;
                        //delay for 2 seconds
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                openResultsActivity();
                            }
                        }, 2000);
                    }

                    if(currentTaps > bestResult) {
                        bestResult = currentTaps;

                        SharedPreferences preferences1 = getSharedPreferences("PREFS", 0);
                        SharedPreferences.Editor editor = preferences1.edit();
                        editor.putInt("highScore", bestResult);
                        editor.apply();
                    }
                }
            };
    }
    public void openPopUpActivity() {
        Intent intent = new Intent(this,PopUpActivity.class);
        intent.putExtra("playerOneTurn", playerOneTurn);
        intent.putExtra("playerOneScore", playerOneScore);
        intent.putExtra("GameInfo", GameRegistry.getGameInfo(ButtonMashActivity.class));
        startActivity(intent);
    }

    public void openResultsActivity() {
        Intent intent = new Intent(this,ResultsActivity.class);
        intent.putExtra("playerOneScore", playerOneScore);
        intent.putExtra("playerTwoScore", playerTwoScore);
        startActivity(intent);
    }
}

