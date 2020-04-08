package com.mewtwo2.settlethescore.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public abstract class GameActivity extends AppCompatActivity {
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
