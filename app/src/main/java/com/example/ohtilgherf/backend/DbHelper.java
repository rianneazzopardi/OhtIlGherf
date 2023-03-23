package com.example.ohtilgherf.backend;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "trivia.db";

    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(createTables());
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(dropTables("questions"));
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

    public String createTables(){
        return "";
    }

    public String dropTables(String tableName){
        return "DROP TABLE IF EXISTS " + tableName;

    }

    public List<String> getDifficulties(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> difficulties = new ArrayList<String>();
        String[] projection = {"difficulty"};
        Cursor cursor = db.query("questions", projection, null, null, null, null, null);
        List<String> unique = difficulties.stream().distinct().collect(Collectors.toList());
        return unique;
    }
}
