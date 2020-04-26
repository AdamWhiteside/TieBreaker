package com.mewtwo2.settlethescore.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mewtwo2.settlethescore.registration.GameRegistry;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RouletteActivity extends GameActivity {

    // sectors of our wheel (look at the image to see the sectors)
    private static final int[] sectors = { 15,
            19, 4, 21, 2, 25, 17, 34,
            6, 27,13, 36, 11, 30, 8,
            23, 10, 5, 24, 16, 33,
            1, 20, 14, 31, 9, 22,
            18, 29, 7, 28, 12, 35,
            3, 26, 0
    };

    private Handler handler = new Handler();

    private boolean playerOneTurn = true;
    private int playerOneScore = 0;
    private int playerTwoScore = 0;

    @BindView(R.id.spinBtn)
    Button spinBtn;
    @BindView(R.id.resultTv)
    TextView resultTv;
    @BindView(R.id.wheel)
    ImageView wheel;
    // We create a Random instance to make our wheel spin randomly
    private static final Random RANDOM = new Random();
    private int degree = 0, degreeOld = 0;
    // We have 36 sectors on the wheel, we divide 360 by this value to have angle for each sector
    // we divide by 2 to have a half sector
    private static final float HALF_SECTOR = 360f / 36f / 2f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
            if (extras != null) {
            playerOneTurn = extras.getBoolean("playerOneTurn");
            playerOneScore = extras.getInt("playerOneScore");

            if(playerOneTurn)
            {
                playerOneScore = -1;
                playerTwoScore = -1;
            }
        }
    }

    @OnClick(R.id.spinBtn)
    public void spin(View v) {
        spinBtn.setEnabled(false);
        degreeOld = degree % 360;
        // we calculate random angle for rotation of our wheel
        degree = RANDOM.nextInt(360) + 720;
        // rotation effect on the center of the wheel
        RotateAnimation rotateAnim = new RotateAnimation(degreeOld, degree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setDuration(3600);
        rotateAnim.setFillAfter(true);
        rotateAnim.setInterpolator(new DecelerateInterpolator());
        rotateAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // we empty the result text view when the animation start
                resultTv.setText("");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // we display the correct sector pointed by the triangle at the end of the rotate animation
                resultTv.setText(getSector(360 - (degree % 360)));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        // we start the animation
        wheel.startAnimation(rotateAnim);
    }

    private String getSector(int degrees) {
        int i = 0;
        String text = null;

        do {
            // start and end of each sector on the wheel
            float start = HALF_SECTOR * (i * 2 + 1);
            float end = HALF_SECTOR * (i * 2 + 3);

            if (degrees >= start && degrees < end) {
                // degrees is in [start;end[
                // so text is equals to sectors[i];
                text = "" + sectors[i];
                if(playerOneTurn == true)
                {
                    playerOneScore = sectors[i];
                }
                else
                {
                    playerTwoScore = sectors[i];
                }
            }

            i++;
            // now we can test our Android Roulette Game :)
            // That's all !
            // In the second part, you will learn how to add some bets on the table to play to the Roulette Game :)
            // Subscribe and stay tuned !

        } while (text == null  &&  i < sectors.length);

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

        return text;
    }

    public void openPopUpActivity() {
        Intent intent = new Intent(this,PopUpActivity.class);
        intent.putExtra("playerOneTurn", playerOneTurn);
        intent.putExtra("playerOneScore", playerOneScore);
        intent.putExtra("GameInfo", GameRegistry.getGameInfo(RouletteActivity.class));
        startActivity(intent);
    }

    public void openResultsActivity() {
        Intent intent = new Intent(this,ResultsActivity.class);
        intent.putExtra("playerOneScore", playerOneScore);
        intent.putExtra("playerTwoScore", playerTwoScore);
        startActivity(intent);
    }

}
