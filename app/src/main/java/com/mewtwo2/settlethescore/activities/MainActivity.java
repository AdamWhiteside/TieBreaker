package com.mewtwo2.settlethescore.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.myfirstapp.R;
import com.mewtwo2.settlethescore.registration.GameInfo;
import com.mewtwo2.settlethescore.registration.GameRegistry;

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
                openPopUpActivity(GameRegistry.getRandomGameInfo());
            }
        });

        btnPickGame = findViewById(R.id.btnPickGame);
        btnPickGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"Pick Game Clicked",Toast.LENGTH_SHORT).show();
                openPickGameActivity();
            }
        });
    }

    public void openPopUpActivity(GameInfo gameToLaunch) {
        Intent intent = new Intent(this,PopUpActivity.class);
        intent.putExtra("playerOneTurn",playerOneTurn);
        intent.putExtra("GameInfo",gameToLaunch);
        startActivity(intent);
    }

    public void openPickGameActivity()
    {
        Intent intent = new Intent(this, PickGameActivity.class);
        startActivity(intent);
    }


    void registerGames()
    {
        //Must add your game here for it to work in the system
        GameRegistry.RegisterGame(
                CoinFlipActivity.class,
                R.string.name_coin_flip,
                R.string.instruction_coin_flip,
                R.drawable.heads,
                GameInfo.GameType.Noninteractive);

        GameRegistry.RegisterGame(
                TicTacToeActivity.class,
                R.string.name_tic_tac_toe,
                R.string.instruction_tic_tac_toe,
                R.drawable.tails,
                GameInfo.GameType.Interactive);
    }

}
