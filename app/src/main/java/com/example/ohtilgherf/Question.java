package com.example.ohtilgherf;

import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class Question {
    public int questionId;
    public String question;
    public String difficulty;
    public String[] answers;
    public String correctAnswer;

    public Question (int id, String question, String difficulty, String correct, String ans_1, String ans_2, String ans_3){
        this.questionId = id;
        this.question = question;
        this.difficulty = difficulty;
        this.correctAnswer = correct;
        this.answers = new String[]{correct, ans_1, ans_2, ans_3};
        List<String> questionList = Arrays.asList(answers);
        Collections.shuffle(questionList);
        questionList.toArray(answers);
    }
}
