package com.example.ohtilgherf.category_selection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ohtilgherf.Category;
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
//        int id = context.getResources().getIdentifier(c.categoryIcon, "drawable", context.getPackageName());
//        Drawable icon = context.getResources().getDrawable(id);
//        b.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);

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
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    String cat = b.getText().toString();
                    intent.putExtra("CATEGORY", cat);
                    intent.putExtra("DIFFICULTY", difficulty);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
