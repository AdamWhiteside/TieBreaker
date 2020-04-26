package com.mewtwo2.settlethescore.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.content.Intent;

//import androidx.appcompat.app.AppCompatActivity;

import com.mewtwo2.settlethescore.registration.GameRegistry;

import java.util.Random;

public class QuickDrawActivity extends GameActivity {
    public boolean topTapped = false;
    public boolean botTapped = false;
    private boolean playerOneTurn = true;
    private int playerOneScore = 0;
    private int playerTwoScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quickdraw);
        /*

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
        */

        startTimer();
    }

    //Declare timer
    CountDownTimer cTimer = null;

    final int random = new Random().nextInt(10000) + 2000;

    //start timer function
    void startTimer() {


        cTimer = new CountDownTimer(random, 250) {

            @Override
            public void onTick(long millisUntilFinished) {
                ImageView PreFanFareRight = (ImageView) findViewById(R.id.PreFanFareRight);
                ImageView PreFanFareLeft = (ImageView) findViewById(R.id.PreFanFareLeft);
                ImageView TimeUpFanFare = (ImageView) findViewById(R.id.TimeUpFanFare);
                TimeUpFanFare.setVisibility(View.INVISIBLE);
                //ViewGroup PFFR = (ViewGroup) PreFanFareRight.getParent();
                //ViewGroup PFFL = (ViewGroup) PreFanFareLeft.getParent();
               // PreFanFareRight.setVisibility(View.INVISIBLE);
                //PreFanFareLeft.setVisibility(View.INVISIBLE);

                /*
                if (PFFR.getVisibility() == View.INVISIBLE) {
                    PFFR.setVisibility(View.INVISIBLE);
                } else {
                    PFFR.setVisibility(View.VISIBLE);
                }

                if (PFFL.getVisibility() == View.INVISIBLE) {
                    PFFL.setVisibility(View.INVISIBLE);
                } else {
                    PFFL.setVisibility(View.VISIBLE);
                }

                 */

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

                TimeUpFanFare.setVisibility(View.VISIBLE);

                PreFanFareLeft.setVisibility(View.INVISIBLE);
                PreFanFareRight.setVisibility(View.INVISIBLE);

                ViewGroup layout = (ViewGroup) imageButton1.getParent();
                ViewGroup layout2 = (ViewGroup) imageButton2.getParent();
                layout.removeView(imageButton1);
                layout2.removeView(imageButton2);
                cancelTimer();
            }
        };
        cTimer.start();
    }

    //cancel timer
    void cancelTimer() {
        if(cTimer!=null)
            cTimer.cancel();
    }

    public void topClick(View view){
        topTapped = true;
    }


    public void openPopUpActivity() {
        Intent intent = new Intent(this,PopUpActivity.class);
        intent.putExtra("playerOneTurn", playerOneTurn);
        intent.putExtra("playerOneScore", playerOneScore);
        intent.putExtra("GameInfo", GameRegistry.getGameInfo(QuickDrawActivity.class));
        startActivity(intent);
    }

    public void openResultsActivity() {
        Intent intent = new Intent(this,ResultsActivity.class);
        intent.putExtra("playerOneScore", playerOneScore);
        intent.putExtra("playerTwoScore", playerTwoScore);
        startActivity(intent);
    }

}
