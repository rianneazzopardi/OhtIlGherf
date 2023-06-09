package com.example.ohtilgherf.category_selection;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ohtilgherf.Category;
import com.example.ohtilgherf.R;
import com.example.ohtilgherf.databinding.FragmentCategoryButtonListBinding;

import java.util.ArrayList;
import java.util.List;

public class category_button_list extends Fragment {
    private CategorySelectionViewModel viewModel;
    private CategoryButtonAdapter adapter;
    private @NonNull FragmentCategoryButtonListBinding binding;
    private RecyclerView categories_view;
    private List<Category> categories = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        viewModel = new ViewModelProvider(this).get(CategorySelectionViewModel.class);
        binding = FragmentCategoryButtonListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        categories_view = root.findViewById(R.id.category_buttons);
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
        viewModel.getDifficulties().observe(getViewLifecycleOwner(), this::updateItemsList);
    }

    private void setUpRecyclerView(){
        adapter = new CategoryButtonAdapter(categories);
        categories_view.setAdapter(adapter);
        categories_view.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
    }

    private void updateItemsList(List<Category> newCategories){
        categories.addAll(newCategories);
        adapter.notifyDataSetChanged();
    }
}