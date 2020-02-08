package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class CoinFlipActivity extends AppCompatActivity {

    public static final Random RANDOM = new Random();
    private Handler handler = new Handler();
    private ImageView coin;
    private Button heads_btn;
    private Button tails_btn;
    private String choice;

    //public static vars are referenced in other classes
    public static boolean playerOneTurn = true;
    public static int playerOneScore = 0;
    public static int playerTwoScore = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_flip);

        //get coin id
        coin = (ImageView) findViewById(R.id.coin);

        //set up heads button
        heads_btn = (Button) findViewById(R.id.heads_btn);
        heads_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice = "heads";

                //disable button presses
                heads_btn.setEnabled(false);
                tails_btn.setEnabled(false);
                flipCoin();
            }
        });
        //set up tail button
        tails_btn = (Button) findViewById(R.id.tails_btn);
        tails_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice = "tails";

                //disable button presses
                heads_btn.setEnabled(false);
                tails_btn.setEnabled(false);
                flipCoin();
            }
        });
        //resetActivityData();
    }

    private void flipCoin() {
        //Animation stuff
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1000);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if (RANDOM.nextFloat() > 0.5f) {
                    coin.setImageResource(R.drawable.tails);
                    //P1 guessed incorrectly
                    if (choice == "heads" && playerOneTurn == true) {
                        Toast.makeText(getApplicationContext(), R.string.sorry_tails_wins, Toast.LENGTH_SHORT).show();

                    }
                    //P1 guessed correctly
                    else if (choice == "tails" && playerOneTurn == true) {
                        Toast.makeText(getApplicationContext(), R.string.tails_wins, Toast.LENGTH_SHORT).show();
                        playerOneScore += 1;
                    }
                    //P2 guessed incorrectly
                    else if (choice == "heads" && playerOneTurn == false) {
                        Toast.makeText(getApplicationContext(), R.string.sorry_tails_wins, Toast.LENGTH_SHORT).show();
                    }
                    //p2 guessed correctly
                    else if (choice == "tails" && playerOneTurn == false) {
                        Toast.makeText(getApplicationContext(), R.string.tails_wins, Toast.LENGTH_SHORT).show();
                        playerTwoScore += 1;
                    }
                }
                else {
                    coin.setImageResource(R.drawable.heads);
                    //P1 guessed correctly
                    if (choice == "heads" && playerOneTurn == true) {
                        Toast.makeText(getApplicationContext(), R.string.heads_wins, Toast.LENGTH_SHORT).show();
                        playerOneScore += 1;
                    }
                    //P1 guessed incorrectly
                    else if (choice == "tails" && playerOneTurn == true) {
                        Toast.makeText(getApplicationContext(), R.string.sorry_heads_wins, Toast.LENGTH_SHORT).show();
                    }
                    //P2 guessed correctly
                    else if (choice == "heads" && playerOneTurn == false) {
                        Toast.makeText(getApplicationContext(), R.string.heads_wins, Toast.LENGTH_SHORT).show();
                        playerTwoScore += 1;
                    }
                    //P2 guessed incorrectly
                    else if (choice == "tails" && playerOneTurn == false) {
                        Toast.makeText(getApplicationContext(), R.string.sorry_heads_wins, Toast.LENGTH_SHORT).show();
                    }
                }

                System.out.println("playerOneScore: " + playerOneScore);
                System.out.println("playerTwoScore: " + playerTwoScore);

                Animation fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(3000);
                fadeIn.setFillAfter(true);

                coin.startAnimation(fadeIn);

                //player one has flipped
                if (playerOneTurn == true) {
                    //now it is player 2's turn
                    playerOneTurn = false;

                    //delay for 2 seconds
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //player 2 must click ready to flip
                            openPopUpActivity();
                        }
                    }, 2000);
                }
                else {
                    //delay for 2 seconds
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            openResultsActivity();
                        }
                    }, 2000);
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        coin.startAnimation(fadeOut);
    }

    public void openPopUpActivity() {
        Intent intent = new Intent(this,PopUpActivity.class);
        startActivity(intent);
    }

    public void openMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void openResultsActivity() {
        Intent intent = new Intent(this,ResultsActivity.class);
        startActivity(intent);
    }
}
