package com.example.ohtilgherf.category_scores;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ohtilgherf.Category;
import com.example.ohtilgherf.R;
import com.example.ohtilgherf.category_selection.CategoryButtonAdapter;
import com.example.ohtilgherf.category_selection.CategorySelectionViewModel;
import com.example.ohtilgherf.databinding.FragmentCategoryButtonListBinding;
import com.example.ohtilgherf.databinding.FragmentCategoryScoreListBinding;


import java.util.ArrayList;
import java.util.List;

public class CategoryScoreList extends Fragment {
    private CategoryScoreViewModel vm;
    private CategoryScoreAdapter adapter;
    private @NonNull FragmentCategoryScoreListBinding binding;
    private RecyclerView scoresView;
    private List<Category> scores = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        vm = new ViewModelProvider(this).get(CategoryScoreViewModel.class);
        binding = FragmentCategoryScoreListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        scoresView = root.findViewById(R.id.category_scores);
        setUpRecyclerView();
        fetchItems();
        return root;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }

    private void fetchItems(){
        vm.getCategoryScores().observe(getViewLifecycleOwner(), this::updateItemsList);
    }

    private void setUpRecyclerView(){
        adapter = new CategoryScoreAdapter(scores);
        scoresView.setAdapter(adapter);
        scoresView.setLayoutManager(new LinearLayoutManager(scoresView.getContext()));
    }

    private void updateItemsList(List<Category> newScores){
        scores.addAll(newScores);
        adapter.notifyDataSetChanged();
    }
}