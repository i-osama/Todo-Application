package com.zeeshan_s.to_doapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class GetStartedScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started_screen);

        AppCompatButton startBtn = findViewById(R.id.flashScreenGetStartButton);
//        -------Shared preference------
        SharedPreferences preferences = getSharedPreferences("getStartedButtonClicked",MODE_PRIVATE);

        startBtn.setOnClickListener(view -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("userClicked",true);
            editor.apply();

            Intent intent = new Intent(GetStartedScreen.this, MainActivity.class);
            startActivity(intent);
        });

    }
}