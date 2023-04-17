package com.example.ohtilgherf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScoreScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_score_screen_landscape);
        } else{
            setContentView(R.layout.activity_score_screen);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Intent intent = getIntent();
        int score = intent.getIntExtra("SCORE", 0);

        TextView scoreTextView = findViewById(R.id.congrats);
        scoreTextView.setText("Congrats! You guessed " + score +"/5 questions!");

        LinearLayout brainContainer = findViewById(R.id.brain_container);
        int margin = getResources().getDimensionPixelSize(R.dimen.brain_margin);
        for (int i = 0; i < score; i++) {
            ImageView brainIcon = new ImageView(this);
            brainIcon.setImageResource(R.drawable.brain);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            if (i < score - 1) {
                params.setMargins(0, 0, margin, 0);
            }
            brainIcon.setLayoutParams(params);
            brainContainer.addView(brainIcon);
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    public void goToDifficultySelectionScreen(View view){
        Intent intent = new Intent(this, DifficultySelection.class);
        startActivity(intent);
    }


    public void goToHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_score_screen_landscape);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_score_screen);
        }
    }
}