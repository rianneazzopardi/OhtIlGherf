package com.example.ohtilgherf.difficulty_selection;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ohtilgherf.CategorySelection;
import com.example.ohtilgherf.R;

import java.util.List;

public class DifficultyButtonAdapter extends RecyclerView.Adapter<DifficultyButtonAdapter.ViewHolder> {
    private List<String> difficulties;

    public DifficultyButtonAdapter(List<String> difficulties){
        this.difficulties = difficulties;
    }

    @NonNull
    @Override
    public DifficultyButtonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.difficulty_button, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DifficultyButtonAdapter.ViewHolder holder, int position) {
        String s = difficulties.get(position);
        Button b = holder.b;
        b.setText(s);
    }

    @Override
    public int getItemCount() {
        return this.difficulties.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public Button b;

        public ViewHolder(final View itemView) {
            super(itemView);
            b = (Button) itemView.findViewById(R.id.difficulty_button);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  Intent intent = new Intent(v.getContext(), CategorySelection.class);
                  intent.putExtra("DIFFICULTY", b.getText().toString());
                  v.getContext().startActivity(intent);
                }
            });
        }
    }
}