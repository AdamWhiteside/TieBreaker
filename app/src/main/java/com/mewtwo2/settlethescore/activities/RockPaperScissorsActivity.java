package com.mewtwo2.settlethescore.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.mewtwo2.settlethescore.registration.GameRegistry;

public class RockPaperScissorsActivity extends AppCompatActivity {

    private Handler handler = new Handler();

    private ImageView rock_paper_scissors;
    private Button rock_btn;
    private Button paper_btn;
    private Button scissors_btn;
    private String player_one_choice;
    private String player_two_choice;
    private String choice;

    private boolean playerOneTurn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rock_paper_scissors);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            playerOneTurn = extras.getBoolean("playerOneTurn");
            player_one_choice = extras.getString("player_one_choice");
        }

        //get image id
        rock_paper_scissors = findViewById(R.id.rock_paper_scissors);

        //set up rock button
        rock_btn = (Button) findViewById(R.id.rock_btn);
        rock_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice = "rock";
                //disable button presses
                rock_btn.setEnabled(false);
                paper_btn.setEnabled(false);
                scissors_btn.setEnabled(false);
                getChoice();
            }
        });

        //set up paper button
        paper_btn = (Button) findViewById(R.id.paper_btn);
        paper_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice = "paper";

                //disable button presses
                rock_btn.setEnabled(false);
                paper_btn.setEnabled(false);
                scissors_btn.setEnabled(false);
                getChoice();;
            }
        });

        //set up rock button
        scissors_btn = (Button) findViewById(R.id.scissors_btn);
        scissors_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice = "scissors";

                //disable button presses
                rock_btn.setEnabled(false);
                paper_btn.setEnabled(false);
                scissors_btn.setEnabled(false);
                getChoice();
            }
        });
    }

    private void getChoice()
    {
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
                switch(choice)
                {
                    case "rock":
                        rock_paper_scissors.setImageResource(R.drawable.rock);
                        Toast.makeText(getApplicationContext(), R.string.picked_rock, Toast.LENGTH_SHORT).show();
                        break;
                    case "paper":
                        rock_paper_scissors.setImageResource(R.drawable.paper);
                        Toast.makeText(getApplicationContext(), R.string.picked_paper, Toast.LENGTH_SHORT).show();
                        break;
                    case "scissors":
                        rock_paper_scissors.setImageResource(R.drawable.scissors);
                        Toast.makeText(getApplicationContext(), R.string.picked_scissors, Toast.LENGTH_SHORT).show();
                        break;
                }
                if(playerOneTurn == true)
                {
                    player_one_choice = choice;
                }
                else
                {
                    player_two_choice = choice;
                }


                Animation fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(3000);
                fadeIn.setFillAfter(true);

                rock_paper_scissors.startAnimation(fadeIn);

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
        rock_paper_scissors.startAnimation(fadeOut);
    }

    public void openPopUpActivity() {
        Intent intent = new Intent(this,PopUpActivity.class);
        intent.putExtra("playerOneTurn", playerOneTurn);
        intent.putExtra("player_one_choice", player_one_choice);
        intent.putExtra("GameInfo", GameRegistry.getGameInfo(RockPaperScissorsActivity.class));
        startActivity(intent);
    }

    public void openResultsActivity() {
        Intent intent = new Intent(this,ResultsActivity.class);
        intent.putExtra("player_one_choice", player_one_choice);
        intent.putExtra("player_two_choice", player_two_choice);
        startActivity(intent);
    }
}
