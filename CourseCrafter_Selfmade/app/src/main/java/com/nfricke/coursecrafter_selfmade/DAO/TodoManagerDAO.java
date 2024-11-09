package com.nfricke.coursecrafter_selfmade.DAO;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class TodoManagerDAO {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static String sharedPreferencesDate = "Data";
    private static String sharedPreferencesString = "tododatafricke";

    //Initialize Data Access Object
    public TodoManagerDAO(Context context){
        sharedPreferences = context.getApplicationContext().getSharedPreferences(sharedPreferencesDate, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    };

    //Save object
    public void saveTodoManager(TodoManager todoManager) {
        Gson gson = new Gson();
        String json = gson.toJson(todoManager);
        editor.putString(sharedPreferencesString,json);
        editor.apply();
    }

    //Read object
    public void readTodoManager(TodoManager todoManager) {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(sharedPreferencesString,null);
        if (json != null) {
            Type type = new TypeToken<TodoManager>(){}.getType();
            TodoManager savedData = gson.fromJson(json, type);
            if (savedData != null) {
                todoManager.addAll(savedData);
            }
        }
    }
}
