package com.example.ohtilgherf.Difficulty_Scores.hard;

import android.annotation.SuppressLint;
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

public class HardFragment extends Fragment {

    private TextView hard_score;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hard, container, false);
        DbHelper helper = new DbHelper(view.getContext());
        hard_score =  (TextView) view.findViewById(R.id.hard_score);
        //setting the text to be displayed on the screen in the TextView
        hard_score.setText("You guessed " + helper.getDifficultyScore("Hard") + " hard questions");
        return view;
    }
}
