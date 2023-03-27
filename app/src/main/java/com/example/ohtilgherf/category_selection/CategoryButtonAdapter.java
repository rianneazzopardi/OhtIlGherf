package com.example.ohtilgherf.category_selection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ohtilgherf.Category;
import com.example.ohtilgherf.GameActivity;
import com.example.ohtilgherf.MainActivity;
import com.example.ohtilgherf.R;

import java.util.List;

public class CategoryButtonAdapter extends RecyclerView.Adapter<CategoryButtonAdapter.ViewHolder>{
    private List<Category> categories;

    public CategoryButtonAdapter(List<Category> categories){
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryButtonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.category_button, parent, false);
        return new CategoryButtonAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category c = categories.get(position);
        Button b = holder.b;
        b.setText(c.categoryName);
        Context context = b.getContext();
        b.setBackgroundColor(ContextCompat.getColor(context, getBackgroundColorSwitch(c)));
    }

    private int getBackgroundColorSwitch(Category c) {
        // Return the corresponding color resource ID based on the item's position or data
        // For example:
        switch (c.categoryName) {
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

    @Override
    public int getItemCount() {
        return this.categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public Button b;

        public ViewHolder(final View itemView) {
            super(itemView);
            b = (Button) itemView.findViewById(R.id.category_button);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent fetch = ((Activity) v.getContext()).getIntent();
                    String difficulty = fetch.getStringExtra("DIFFICULTY");
                    Intent intent = new Intent(v.getContext(), GameActivity.class);
                    String cat = b.getText().toString();
                    intent.putExtra("CATEGORY", cat);
                    intent.putExtra("DIFFICULTY", difficulty);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
