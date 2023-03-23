package com.example.ohtilgherf;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DifficultySelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_selection_portrait);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

    }

}