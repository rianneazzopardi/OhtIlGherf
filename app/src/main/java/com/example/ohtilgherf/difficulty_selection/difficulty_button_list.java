package com.example.ohtilgherf.difficulty_selection;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ohtilgherf.databinding.FragmentDifficultyButtonListBinding;

import com.example.ohtilgherf.R;

import java.util.ArrayList;
import java.util.List;

public class difficulty_button_list extends Fragment {
    private DifficultySelectionViewModel viewModel;
    private DifficultyButtonAdapter adapter;
    private @NonNull FragmentDifficultyButtonListBinding binding;
    private RecyclerView difficulty_view;
    private List<String> difficulties = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        viewModel = new ViewModelProvider(this).get(DifficultySelectionViewModel.class);
        binding = FragmentDifficultyButtonListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        difficulty_view = root.findViewById(R.id.difficulty_buttons);
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
        adapter = new DifficultyButtonAdapter(difficulties);
        difficulty_view.setAdapter(adapter);
        difficulty_view.setLayoutManager(new LinearLayoutManager(difficulty_view.getContext()));
    }

    private void updateItemsList(List<String> newDifficulties){
        difficulties.addAll(newDifficulties);
        adapter.notifyDataSetChanged();
    }
}
