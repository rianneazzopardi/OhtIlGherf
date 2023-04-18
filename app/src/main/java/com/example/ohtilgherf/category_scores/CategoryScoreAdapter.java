package com.example.ohtilgherf.category_scores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ohtilgherf.R;
import com.example.ohtilgherf.Category;

import java.util.List;

public class CategoryScoreAdapter extends RecyclerView.Adapter<CategoryScoreAdapter.ViewHolder>{
    private List<Category> scores;
    View itemView;

    public CategoryScoreAdapter(List<Category> scores){
        this.scores = scores;
    }

    @NonNull
    @Override
    public CategoryScoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        itemView = inflater.inflate(R.layout.category_score_card, parent, false);
        return new CategoryScoreAdapter.ViewHolder(itemView);
    }

    //a card is created for each category in the database displaying the score per category with the name of the category
    @Override
    public void onBindViewHolder(@NonNull CategoryScoreAdapter.ViewHolder holder, int position) {
        Category c = scores.get(position);
        TextView name = holder.category_name;
        TextView score = holder.category_score;
        name.setText(c.categoryName);
        score.setText(c.categoryScore);
        Context context = itemView.getContext();
        itemView.setBackgroundColor(ContextCompat.getColor(context, getBackgroundColorSwitch(c.categoryName)));
    }

    //returning the corresponding color resource ID based on the category
    private int getBackgroundColorSwitch(String categoryName) {
        switch (categoryName) {
            case "Arts":
                return R.color.arts;
            case "Film & TV":
                return R.color.film;
            case "Food & Drink":
                return R.color.food;
            case "General Knowledge":
                return R.color.general;
            case "Geography":
                return R.color.geo;
            case "History":
                return R.color.history;
            case "Music":
                return R.color.music;
            case "Science":
                return R.color.science;
            case "Culture":
                return R.color.culture;
            case "Sport":
                return R.color.sport;
            default:
                return R.color.button;

        }
    }
    public int getItemCount() {
        return this.scores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView category_name;
        public TextView category_score;

        public ViewHolder(final View itemView) {
            super(itemView);
            category_name = (TextView) itemView.findViewById(R.id.category_score_name);
            category_score = (TextView) itemView.findViewById(R.id.category_score);
        }
    }
}