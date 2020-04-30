package com.mewtwo2.settlethescore.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mewtwo2.settlethescore.registration.GameInfo;
import com.mewtwo2.settlethescore.registration.GameRegistry;

public class MainActivity extends AppCompatActivity {

    //Ensures games are not registered more than once.
    private static boolean registrationComplete = false;

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
        if (registrationComplete)
            return;

        //Must add your game here for it to work in the system
        GameRegistry.RegisterGame(
                CoinFlipActivity.class,
                R.string.name_coin_flip,
                R.string.instruction_coin_flip,
                R.drawable.cointosslogo,
                GameInfo.GameType.Noninteractive);

        GameRegistry.RegisterGame(
                TicTacToeActivity.class,
                R.string.name_tic_tac_toe,
                R.string.instruction_tic_tac_toe,
                R.drawable.tictactoelogo,
                GameInfo.GameType.Interactive);

        GameRegistry.RegisterGame(
                RockPaperScissorsActivity.class,
                R.string.name_rock_paper_scissors,
                R.string.instruction_rock_paper_scissors,
                R.drawable.rpclogo,
                GameInfo.GameType.Noninteractive);

        GameRegistry.RegisterGame(
            RouletteActivity.class,
            R.string.name_roulette,
            R.string.instruction_roulette,
            R.drawable.roulettelogo,
            GameInfo.GameType.Noninteractive);

        GameRegistry.RegisterGame(
                ButtonMashActivity.class,
                R.string.name_button_mash,
                R.string.instruction_button_mash,
                R.drawable.buttonmashlogo,
                GameInfo.GameType.Noninteractive);

        GameRegistry.RegisterGame(
                QuickDrawActivity.class,
                R.string.name_roulette,
                R.string.instruction_roulette,
                R.drawable.roulettelogo,
                GameInfo.GameType.Interactive);

        registrationComplete = true;
    }

}
