package com.mewtwo2.settlethescore.activities;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.Toast;
import android.os.Handler;

import java.util.Random;

// Quick Draw game class
public class QuickDrawActivity extends GameActivity {
    private Handler handler = new Handler();
    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private ImageButton QuickButton1;
    private ImageButton QuickButton2;
    private ImageButton CoverButton1;
    private ImageButton CoverButton2;
    private ImageView PlayerOneWinsImage;
    private ImageView PlayerTwoWinsImage;
    private ImageView PlayerOneMisfire;
    private ImageView PlayerTwoMisfire;
    private ImageView TimeUpFanFare;
    private ImageView FanFareLeft;
    private ImageView FanFareRight;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // declarations
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quickdraw);
        QuickButton1 = findViewById(R.id.QuickButton1);
        QuickButton2 = findViewById(R.id.QuickButton2);
        CoverButton1 = findViewById(R.id.CoverButton1);
        CoverButton2 = findViewById(R.id.CoverButton2);
        PlayerOneWinsImage = findViewById(R.id.PlayerOneWinsImage);
        PlayerTwoWinsImage = findViewById(R.id.PlayerTwoWinsImage);
        PlayerOneMisfire = findViewById(R.id.PlayerOneMisfire);
        PlayerTwoMisfire = findViewById(R.id.PlayerTwoMisfire);
        TimeUpFanFare = findViewById(R.id.TimeUpFanFare);
        FanFareLeft = findViewById(R.id.PreFanFareLeft);
        FanFareRight = findViewById(R.id.PreFanFareRight);

        PlayerOneWinsImage.setVisibility(View.INVISIBLE);
        PlayerTwoWinsImage.setVisibility(View.INVISIBLE);
        PlayerOneMisfire.setVisibility(View.INVISIBLE);
        PlayerTwoMisfire.setVisibility(View.INVISIBLE);

        // buttons disabled until timer is up
        QuickButton1.setEnabled(false);
        QuickButton2.setEnabled(false);

        // timer determines when it is appropriate to tap
        startTimer();

        // Player one win button listener, tapping this sees player one win
        QuickButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerOneScore = 1;

                // disable buttons on first click
                QuickButton1.setEnabled(false);
                QuickButton2.setEnabled(false);

                QuickButton1.setVisibility(View.INVISIBLE);
                QuickButton2.setVisibility(View.INVISIBLE);
                CoverButton1.setVisibility(View.INVISIBLE);
                CoverButton2.setVisibility(View.INVISIBLE);
                TimeUpFanFare.setVisibility(View.INVISIBLE);
                FanFareLeft.setVisibility(View.INVISIBLE);
                FanFareRight.setVisibility(View.INVISIBLE);

                // display proper victory image
                PlayerOneWinsImage.setVisibility(View.VISIBLE);

                // disable fanfare
                cancelTimer();

                Toast.makeText(getApplicationContext(), R.string.player_one_pressed, Toast.LENGTH_SHORT).show();

                // delay before changing screens
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        openResultsActivity();
                    }
                }, 2000);
            }
        });

        // Player two win button listener, tapping this sees player two win
        QuickButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerTwoScore = 1;

                // disable buttons on first click
                QuickButton1.setEnabled(false);
                QuickButton2.setEnabled(false);

                QuickButton1.setVisibility(View.INVISIBLE);
                QuickButton2.setVisibility(View.INVISIBLE);
                CoverButton1.setVisibility(View.INVISIBLE);
                CoverButton2.setVisibility(View.INVISIBLE);
                TimeUpFanFare.setVisibility(View.INVISIBLE);
                FanFareLeft.setVisibility(View.INVISIBLE);
                FanFareRight.setVisibility(View.INVISIBLE);

                // display proper victory image
                PlayerTwoWinsImage.setVisibility(View.VISIBLE);

                // disable fanfare
                cancelTimer();

                Toast.makeText(getApplicationContext(), R.string.player_two_pressed, Toast.LENGTH_SHORT).show();

                // delay before changing screens
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        openResultsActivity();
                    }
                }, 2000);
            }
        });

        // Player one lose button listener, tapping this sees player two win
        CoverButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerTwoScore = 1;

                //disable button presses
                QuickButton1.setEnabled(false);
                QuickButton2.setEnabled(false);
                CoverButton1.setEnabled(false);
                CoverButton2.setEnabled(false);

                QuickButton1.setVisibility(View.INVISIBLE);
                QuickButton2.setVisibility(View.INVISIBLE);
                CoverButton1.setVisibility(View.INVISIBLE);
                CoverButton2.setVisibility(View.INVISIBLE);
                TimeUpFanFare.setVisibility(View.INVISIBLE);
                FanFareLeft.setVisibility(View.INVISIBLE);
                FanFareRight.setVisibility(View.INVISIBLE);

                PlayerOneMisfire.setVisibility(View.VISIBLE);

                cancelTimer();

                Toast.makeText(getApplicationContext(), R.string.player_one_too_soon, Toast.LENGTH_SHORT).show();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        openResultsActivity();
                    }
                }, 2000);
            }
        });

        // Player two lose button listener, tapping this sees player one win
        CoverButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerOneScore = 1;

                //disable button presses
                QuickButton1.setEnabled(false);
                QuickButton2.setEnabled(false);
                CoverButton1.setEnabled(false);
                CoverButton2.setEnabled(false);

                QuickButton1.setVisibility(View.INVISIBLE);
                QuickButton2.setVisibility(View.INVISIBLE);
                CoverButton1.setVisibility(View.INVISIBLE);
                CoverButton2.setVisibility(View.INVISIBLE);
                TimeUpFanFare.setVisibility(View.INVISIBLE);
                FanFareLeft.setVisibility(View.INVISIBLE);
                FanFareRight.setVisibility(View.INVISIBLE);

                PlayerTwoMisfire.setVisibility(View.VISIBLE);

                cancelTimer();

                Toast.makeText(getApplicationContext(), R.string.player_two_too_soon, Toast.LENGTH_SHORT).show();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        openResultsActivity();
                    }
                }, 2000);
            }
        });



    }

    CountDownTimer timer = null;

    final int random = new Random().nextInt(10000) + 2000;

    // timer for when to press buttons and display fanfare
    void startTimer() {


        timer = new CountDownTimer(random, 250) {

            @Override
            public void onTick(long millisUntilFinished) {
                ImageView PreFanFareRight = (ImageView) findViewById(R.id.PreFanFareRight);
                ImageView PreFanFareLeft = (ImageView) findViewById(R.id.PreFanFareLeft);
                ImageView TimeUpFanFare = (ImageView) findViewById(R.id.TimeUpFanFare);
                TimeUpFanFare.setVisibility(View.INVISIBLE);


                // flash fanfare back and forth
                if (PreFanFareRight.getVisibility() == View.INVISIBLE) {
                    PreFanFareRight.setVisibility(View.VISIBLE);
                } else {
                    PreFanFareRight.setVisibility(View.INVISIBLE);
                }

                if (PreFanFareLeft.getVisibility() == View.INVISIBLE) {
                    PreFanFareLeft.setVisibility(View.VISIBLE);
                } else {
                    PreFanFareLeft.setVisibility(View.INVISIBLE);
                }


            }

            public void onFinish() {
                ImageView PreFanFareRight = (ImageView) findViewById(R.id.PreFanFareRight);
                ImageView PreFanFareLeft = (ImageView) findViewById(R.id.PreFanFareLeft);
                ImageButton imageButton1 = findViewById(R.id.CoverButton1);
                ImageButton imageButton2 = findViewById(R.id.CoverButton2);
                ImageView TimeUpFanFare = (ImageView) findViewById(R.id.TimeUpFanFare);

                // Show time up fanfare and hide pre-fanfare
                TimeUpFanFare.setVisibility(View.VISIBLE);

                PreFanFareLeft.setVisibility(View.INVISIBLE);
                PreFanFareRight.setVisibility(View.INVISIBLE);

                ViewGroup layout = (ViewGroup) imageButton1.getParent();
                ViewGroup layout2 = (ViewGroup) imageButton2.getParent();
                layout.removeView(imageButton1);
                layout2.removeView(imageButton2);

                // Swap available buttons, allowing potential victory
                CoverButton1.setEnabled(false);
                CoverButton2.setEnabled(false);
                QuickButton1.setEnabled(true);
                QuickButton2.setEnabled(true);

                cancelTimer();
            }
        };
        timer.start();
    }

    //cancel timer
    void cancelTimer() {
        if(timer!=null)
            timer.cancel();
    }

    public void topClick(View view){
        playerOneScore = 1;
    }


    // Finish game, record score
    public void openResultsActivity() {
        Intent intent = new Intent(this,ResultsActivity.class);
        intent.putExtra("playerOneScore", playerOneScore);
        intent.putExtra("playerTwoScore", playerTwoScore);
        startActivity(intent);
    }

}
