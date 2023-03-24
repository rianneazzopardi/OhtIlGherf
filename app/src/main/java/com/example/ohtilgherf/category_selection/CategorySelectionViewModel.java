package com.example.ohtilgherf.category_selection;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.ohtilgherf.Category;
import com.example.ohtilgherf.backend.DbHelper;

import java.util.List;

public class CategorySelectionViewModel extends AndroidViewModel {
    private MutableLiveData<List<Category>> categories;
    private DbHelper helper;

    public CategorySelectionViewModel(Application app) {
        super(app);
        helper = new DbHelper(app.getApplicationContext());
        categories = new MutableLiveData<>();
    }

    public MutableLiveData<List<Category>> getDifficulties() {
        List<Category> categoryList = helper.getAllCategories();
        categories.setValue(categoryList);
        return categories;
    }

}
