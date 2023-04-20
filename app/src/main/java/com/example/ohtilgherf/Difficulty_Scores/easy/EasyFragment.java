package com.example.ohtilgherf.Difficulty_Scores.easy;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ohtilgherf.R;
import com.example.ohtilgherf.backend.DbHelper;

public class EasyFragment extends Fragment {

    private TextView easy_score;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_easy, container, false);
        DbHelper helper = new DbHelper(view.getContext());
        easy_score =  (TextView) view.findViewById(R.id.easy_score);
        //setting the text to be displayed on the screen in the TextView
        easy_score.setText("You guessed " + helper.getDifficultyScore("Easy") + " easy questions");
        return view;
    }
}