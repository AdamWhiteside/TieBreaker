package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSettleTheScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSettleTheScore = findViewById(R.id.btnSettleTheScore);
        btnSettleTheScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTicTacToeActivity();
                //openPopUpActivity();
            }
        });
    }
    public void openPopUpActivity() {
        Intent intent = new Intent(this,PopUpActivity.class);
        startActivity(intent);
    }

    public void openTicTacToeActivity() {
        Intent intent = new Intent(this, TicTacToeActivity.class);
        startActivity(intent);
    }
}

