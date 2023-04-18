package com.example.ohtilgherf.ui.medium;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.ohtilgherf.R;
import com.example.ohtilgherf.backend.DbHelper;

public class MediumFragment extends Fragment {

    private TextView score;


    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medium, container, false);
        DbHelper helper = new DbHelper(view.getContext());
        score =  (TextView) view.findViewById(R.id.medium_score);
        //setting the text to be displayed on the screen in the TextView
        score.setText("You guessed " + helper.getDifficultyScore("Medium") + " medium questions");

        return view;
    }
}