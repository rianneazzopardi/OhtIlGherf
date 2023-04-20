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
import com.example.ohtilgherf.databinding.FragmentCategoryScoreListBinding;


import java.util.ArrayList;
import java.util.List;

public class CategoryScoreList extends Fragment {
    private CategoryScoreViewModel viewModel;
    private CategoryScoreAdapter adapter;
    private @NonNull FragmentCategoryScoreListBinding binding;
    private RecyclerView scores_view;
    private List<Category> scores = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        viewModel = new ViewModelProvider(this).get(CategoryScoreViewModel.class);
        binding = FragmentCategoryScoreListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        scores_view = root.findViewById(R.id.category_scores);
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
        viewModel.getCategoryScores().observe(getViewLifecycleOwner(), this::updateItemsList);
    }

    private void setUpRecyclerView(){
        adapter = new CategoryScoreAdapter(scores);
        scores_view.setAdapter(adapter);
        scores_view.setLayoutManager(new LinearLayoutManager(scores_view.getContext()));
    }

    private void updateItemsList(List<Category> newScores){
        scores.addAll(newScores);
        adapter.notifyDataSetChanged();
    }
}