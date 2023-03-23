package com.example.ohtilgherf.difficulty_selection;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class DifficultySelectionViewModel extends ViewModel {
    private MutableLiveData<List<String>> difficulties;

    public DifficultySelectionViewModel() {
        difficulties = new MutableLiveData<>();
    }

    public MutableLiveData<List<String>> getDifficulties() {
        List<String> difficultyList = new ArrayList<>();
        difficultyList.add("Easy");
        difficultyList.add("Medium");
        difficultyList.add("Hard");
        difficulties.setValue(difficultyList);
        return difficulties;
    }
}