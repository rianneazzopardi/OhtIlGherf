package com.example.ohtilgherf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.ohtilgherf.backend.DbHelper;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    protected int i = 0;
    protected int score = 0;
    protected ArrayList<Question> questions = new ArrayList<Question>();
    protected TextView question;
    protected TextView index;
    protected Button option_1;
    protected Button option_2;
    protected Button option_3;
    protected Button option_4;
    protected Resources res;
    DbHelper helper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Intent fetch =  getIntent();
        String difficulty = fetch.getStringExtra("DIFFICULTY");
        String category = fetch.getStringExtra("CATEGORY");

        helper = new DbHelper(this);
        int categoryID = helper.getCategoryID(category);
        questions = helper.getFiveQuestions(difficulty, categoryID);

        question = (TextView)findViewById(R.id.question);
        index = (TextView)findViewById(R.id.question_index);


        option_1 = (Button) findViewById(R.id.button1);
        option_2 = (Button) findViewById(R.id.button2);
        option_3 = (Button) findViewById(R.id.button3);
        option_4 = (Button) findViewById(R.id.button4);

        res = getResources();

        QuestionRound(questions);



    }
    protected void QuestionRound(@NonNull ArrayList<Question> questions){
        Question q = questions.get(i);

        question.setText(q.question);

        index.setText((i+1) + "/" + questions.size());

        Resources res = getResources();



        int button = res.getColor(R.color.button);

        option_1.setBackgroundTintList(ColorStateList.valueOf(button));
        option_1.setText(q.answers[0]);

        option_1.setOnClickListener(checkAnswer);

        option_2.setBackgroundTintList(ColorStateList.valueOf(button));
        option_2.setText(q.answers[1]);

        option_2.setOnClickListener(checkAnswer);

        option_3.setBackgroundTintList(ColorStateList.valueOf(button));
        option_3.setText(q.answers[2]);

        option_3.setOnClickListener(checkAnswer);

        option_4.setBackgroundTintList(ColorStateList.valueOf(button));
        option_4.setText(q.answers[3]);

        option_4.setOnClickListener(checkAnswer);
    }

    View.OnClickListener checkAnswer = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            int wrong = res.getColor(R.color.wrong);
            int correct = res.getColor(R.color.correct);

            Button b = (Button) view;
            if(b.getText() == questions.get(i).correctAnswer){
                b.setBackgroundTintList(ColorStateList.valueOf(correct));
                helper.setCorrectAnswered(questions.get(i).questionId);
                score++;
            }
            else{
                b.setBackgroundTintList(ColorStateList.valueOf(wrong));
            }


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run(){
                    i++;
                    if (i < 5) {
                        QuestionRound(questions);
                    } else {
                        if(score > helper.getHighScore()){
                            helper.updateHighScore(score);
                        }

                        Intent intent = new Intent(GameActivity.this, ScoreScreen.class);
                        intent.putExtra("SCORE", score);
                        startActivity(intent);
                    }
                }
            }, 2000);
        }
    };



}