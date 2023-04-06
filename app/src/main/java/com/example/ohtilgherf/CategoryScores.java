package com.example.ohtilgherf;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CategoryScores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category_scores);

        // Removing the app bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }


}