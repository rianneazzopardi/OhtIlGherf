package com.example.ohtilgherf.backend;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ohtilgherf.Category;
import com.example.ohtilgherf.Question;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "trivia.db";

    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS \"categories\" " +
                "(\"categoryID\" INTEGER PRIMARY KEY, " +
                "\"categoryName\" TEXT NOT NULL, " +
                "\"colour\" TEXT NOT NULL);"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS \"questions\" (" +
                "\"questionID\" INTEGER PRIMARY KEY, " +
                "\"question\" TEXT NOT NULL, " +
                "\"difficulty\" TEXT NOT NULL, " +
                "\"answer_1\" TEXT NOT NULL, " +
                "\"answer_2\" TEXT NOT NULL, " +
                "\"answer_3\" TEXT NOT NULL, " +
                "\"correctAnswer\" TEXT NOT NULL, " +
                "\"answeredCorrectly\" INTEGER NOT NULL DEFAULT 0, " +
                "\"categoryID\" INTEGER, " +
                "FOREIGN KEY(\"categoryID\") REFERENCES \"categories\"(\"categoryID\"));"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS \"difficulties\" (" +
                "\"difficultyLevel\" INTEGER, " +
                "\"difficulty\" TEXT PRIMARY KEY);");

        String[] insertions = Insertions.insertions;
        for(int i = 0; i<insertions.length; i++){
            db.execSQL(insertions[i]);
        }

    }



    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(dropTables("questions"));
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

    public String dropTables(String tableName){
        return "DROP TABLE IF EXISTS " + tableName;

    }

    public List<String> getDifficulties(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> difficulties = new ArrayList<String>();
        String[] projection = {"difficulty"};
        String sortOrder = "difficultyLevel ASC";
        Cursor cursor = db.query("difficulties", projection, null, null, null, null, sortOrder);
        System.out.println(cursor.getCount());
        while(cursor.moveToNext()){
            String diff = cursor.getString(cursor.getColumnIndexOrThrow("difficulty"));
            difficulties.add(diff);
        }
        return difficulties;
    }

    public List<Category> getAllCategories(){
        ArrayList<Category> categories = new ArrayList<Category>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"categoryID", "categoryName"};
        Cursor cursor = db.query("categories", projection, null, null, null, null, null);

        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("categoryID"));
            String category = cursor.getString(cursor.getColumnIndexOrThrow("categoryName"));
            Category toAdd = new Category(id, category);
            categories.add(toAdd);
        }
        List<Category> unique = categories.stream().distinct().collect(Collectors.toList());
        return unique;
    }
    public int getCategoryID(String category){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"categoryID"};
        String selection = "categoryName = ?";
        String[] selectionArgs = {category};
        Cursor cursor = db.query("categories", projection, selection, selectionArgs, null, null, null);
        int id = 0;
        while(cursor.moveToNext()){
            id = cursor.getInt(cursor.getColumnIndexOrThrow("categoryID"));
        }
        return id;
    }

    public ArrayList<Question> getFiveQuestions(String difficulty, int category){
        ArrayList<Question> questions = new ArrayList<Question>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"questionID", "question", "answer_1", "answer_2", "answer_3", "correctAnswer"};
        String selection = "difficulty = ? AND categoryID = ?";
        String[] selectionArgs = {difficulty, Integer.toString(category)};
        Cursor cursor = db.query("questions", projection, selection, selectionArgs, null, null, null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("questionID"));
            String question = cursor.getString(cursor.getColumnIndexOrThrow("question"));
            String ans_1 = cursor.getString(cursor.getColumnIndexOrThrow("answer_1"));
            String ans_2 = cursor.getString(cursor.getColumnIndexOrThrow("answer_2"));
            String ans_3 = cursor.getString(cursor.getColumnIndexOrThrow("answer_3"));
            String correct = cursor.getString(cursor.getColumnIndexOrThrow("correctAnswer"));

            Question toAdd = new Question(id, question, difficulty, correct, ans_1, ans_2, ans_3);
            questions.add(toAdd);
        }
        Collections.shuffle(questions);
        ArrayList<Question> toReturn = new ArrayList<Question>();
        for(int i = 0; i<5; i++){
            toReturn.add(questions.get(i));
        }
        return toReturn;
    }

    public void setCorrectAnswered(int questionId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues dataToUpdate = new ContentValues();
        dataToUpdate.put("answeredCorrectly", 1);
        String selection = "questionID = ?";
        String[] selectionArgs = {Integer.toString(questionId)};
        db.update("questions", dataToUpdate, selection, selectionArgs);
    }

}