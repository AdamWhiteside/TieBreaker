package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.mewtwo2.settlethescore.GameInfo;
import com.mewtwo2.settlethescore.GameRegistry;

public class MainActivity extends AppCompatActivity {

    Button btnSettleTheScore;
    Button btnPickGame;
    private boolean playerOneTurn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Register games with the system
        registerGames();

        setContentView(R.layout.activity_main);
        btnSettleTheScore = (Button) findViewById(R.id.btnSettleTheScore);
        btnSettleTheScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPopUpActivity(GameRegistry.getRandomRegistration());
            }
        });

        btnPickGame = findViewById(R.id.btnPickGame);
        btnPickGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Pick Game Clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openPopUpActivity(GameInfo gameToLaunch) {
        Intent intent = new Intent(this,PopUpActivity.class);
        intent.putExtra("playerOneTurn",playerOneTurn);
        intent.putExtra("GameInfo",gameToLaunch);
        startActivity(intent);
    }

    void registerGames()
    {
        //Must add your game here for it to work in the system
        GameRegistry.RegisterGame(CoinFlipActivity.class, R.string.instruction_coin_flip, R.drawable.heads);
    }

}
