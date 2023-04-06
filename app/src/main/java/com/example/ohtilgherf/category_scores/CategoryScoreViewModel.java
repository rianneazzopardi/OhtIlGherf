package com.example.ohtilgherf.category_scores;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.ohtilgherf.Category;
import com.example.ohtilgherf.backend.DbHelper;

import java.util.List;

public class CategoryScoreViewModel extends AndroidViewModel {
    private MutableLiveData<List<Category>> scores;
    private DbHelper helper;

    public CategoryScoreViewModel(Application app) {
        super(app);
        helper = new DbHelper(app.getApplicationContext());
        scores = new MutableLiveData<>();
    }

    public MutableLiveData<List<Category>> getCategoryScores() {
        List<Category> scoreList = helper.getAllCategoryScores();
        scores.setValue(scoreList);
        return scores;
    }
}