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

public class ButtonMashActivity extends AppCompatActivity{
    ImageView arcadeButton;
    TextView result, info;

    int currentTaps = 0;
    boolean gameStarted = false;

    CountDownTimer timer;

    int bestResult = 0;

    int playerOneScore = 0;
    int playerTwoScore = 0;
    int turn = 0;
    boolean playerOneTurn = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttonmash);

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

            timer = new CountDownTimer(10000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long timeTillEnd = (millisUntilFinished / 1000) + 1;
                    result.setText("Time Remaining: " + timeTillEnd);
                }
                @Override
                public void onFinish() {
                    arcadeButton.setEnabled(false);
                    gameStarted = false;
                    info.setText("Game Over");
                    result.setText("Current Taps: " + currentTaps);
                    if(turn == 0)
                        playerOneScore = currentTaps;
                    else if (turn == 1)
                        playerTwoScore = currentTaps;
                    else
                        openResultsActivity();
                    turn++;
                    if(currentTaps > bestResult) {
                        bestResult = currentTaps;

                        SharedPreferences preferences1 = getSharedPreferences("PREFS", 0);
                        SharedPreferences.Editor editor = preferences1.edit();
                        editor.putInt("highScore", bestResult);
                        editor.apply();
                    }
                   /* if (playerOneTurn == true) {
                        //now it is player 2's turn
                        playerOneTurn = false;

                        //delay for 2 seconds
                        Handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //player 2 must click ready to flip
                                openPopUpActivity();
                            }
                        }, 2000);
                    }
                    else {
                        //delay for 2 seconds
                        Handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                openResultsActivity();
                            }
                        }, 2000);
                    }*/
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            arcadeButton.setEnabled(true);
                            info.setText("Start Tapping");
                            currentTaps = 0;
                        }
                    }, 2000);
                }
            };
    }
    public void openPopUpActivity() {
        Intent intent = new Intent(this,PopUpActivity.class);
        intent.putExtra("playerOneTurn", playerOneTurn);
        intent.putExtra("playerOneScore", playerOneScore);
        intent.putExtra("GameInfo", GameRegistry.getGameInfo(CoinFlipActivity.class));
        startActivity(intent);
    }

    public void openResultsActivity() {
        Intent intent = new Intent(this,ResultsActivity.class);
        intent.putExtra("playerOneScore", playerOneScore);
        intent.putExtra("playerTwoScore", playerTwoScore);
        startActivity(intent);
    }
}

