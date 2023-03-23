package com.example.ohtilgherf.difficulty_selection;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ohtilgherf.databinding.FragmentDifficultyButtonListBinding;

import com.example.ohtilgherf.R;

import java.util.ArrayList;
import java.util.List;

public class difficulty_button_list extends Fragment {
    private DifficultySelectionViewModel vm;
    private DifficultyButtonAdapter adapter;
    private @NonNull FragmentDifficultyButtonListBinding binding;
    private RecyclerView difficultyView;
    private List<String> difficulties = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        vm = new ViewModelProvider(this).get(DifficultySelectionViewModel.class);
        binding = FragmentDifficultyButtonListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        difficultyView = root.findViewById(R.id.difficulty_buttons);
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
        vm.getDifficulties().observe(getViewLifecycleOwner(), this::updateItemsList);
    }


    private void setUpRecyclerView(){
        adapter = new DifficultyButtonAdapter(difficulties);
        difficultyView.setAdapter(adapter);
        difficultyView.setLayoutManager(new LinearLayoutManager(difficultyView.getContext()));
    }

    private void updateItemsList(List<String> newDifficulties){
        difficulties.addAll(newDifficulties);
        adapter.notifyDataSetChanged();
    }
}
