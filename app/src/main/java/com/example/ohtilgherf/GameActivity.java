package com.example.ohtilgherf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ohtilgherf.backend.DbHelper;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    protected int i = 0;
    protected int score = 0;
    protected ArrayList<Question> questions = new ArrayList<Question>();
    protected TextView question;
    protected TextView question_no;
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
        //setting the layout according to the orientation of the screen
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_game_landscape);
        } else{
            setContentView(R.layout.activity_game);
        }
        //removing the app bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //getting the difficulty and category chosen by the user in the previous screen
        Intent fetch =  getIntent();
        String difficulty = fetch.getStringExtra("DIFFICULTY");
        String category = fetch.getStringExtra("CATEGORY");

        helper = new DbHelper(this);
        int categoryID = helper.getCategoryID(category);
        questions = helper.getFiveQuestions(difficulty, categoryID);

        //setting a TextView to display the question
        question = (TextView)findViewById(R.id.question);
        //setting a TextView to display the index of the question
        question_no = (TextView)findViewById(R.id.question_index);

        //setting the answers to be chosen by the user as buttons
        option_1 = (Button) findViewById(R.id.button1);
        option_2 = (Button) findViewById(R.id.button2);
        option_3 = (Button) findViewById(R.id.button3);
        option_4 = (Button) findViewById(R.id.button4);

        res = getResources();

        QuestionRound(questions);



    }
    //caters for a round of questions as well as checks the answer
    protected void QuestionRound(@NonNull ArrayList<Question> questions){
        Question q = questions.get(i);

        question.setText(q.question);

        question_no.setText((i+1) + "/" + questions.size());

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
            //setting the colours that the selected button will be displayed as when checked
            //red denotes wrong answer
            //green denotes correct answer
            int wrong = res.getColor(R.color.wrong, null);
            int correct = res.getColor(R.color.correct, null);

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
                        //passes the score through an intent
                        intent.putExtra("SCORE", score);
                        startActivity(intent);
                    }
                }
            }, 2000);
        }
    };

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //setting the layout according to the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_game_landscape);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_game);
        }
    }



}