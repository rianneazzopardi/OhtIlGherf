package com.example.ohtilgherf;

public class Category {
    public int categoryId;
    public String categoryName;
    public String categoryScore;

    public Category(int id, String name){
        this.categoryId = id;
        this.categoryName = name;
    }

    public Category(int id, String name, String score){
        this.categoryId = id;
        this.categoryName = name;
        this.categoryScore = score;
    }
}
