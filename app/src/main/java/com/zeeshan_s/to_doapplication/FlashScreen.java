package com.zeeshan_s.to_doapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class FlashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);

        new Handler().postDelayed((Runnable) () -> {

            Intent intent;

            SharedPreferences preferences = getSharedPreferences("getStartedButtonClicked",MODE_PRIVATE);
            boolean isClicked = preferences.getBoolean("userClicked", false);

            if (isClicked){
                intent = new Intent(FlashScreen.this, MainActivity.class);
            }else {
                intent = new Intent(FlashScreen.this, GetStartedScreen.class);}
            startActivity(intent);
            finish();

        }, 1400);
    }
}