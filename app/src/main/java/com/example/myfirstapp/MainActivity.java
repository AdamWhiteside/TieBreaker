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

public class MainActivity extends AppCompatActivity {

    Button btnSettleTheScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSettleTheScore = (Button) findViewById(R.id.btnSettleTheScore);
        btnSettleTheScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPopUpActivity();
            }
        });
    }
    public void openPopUpActivity() {
        Intent intent = new Intent(this,PopUpActivity.class);
        startActivity(intent);
    }

}
