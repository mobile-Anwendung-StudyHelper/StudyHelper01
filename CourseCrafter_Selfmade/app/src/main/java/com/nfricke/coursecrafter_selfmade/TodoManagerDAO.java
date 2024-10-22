package com.nfricke.coursecrafter_selfmade;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class TodoManagerDAO {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static String sharedPreferencesDate = "Data2";
    private static String sharedPreferencesString = "tododatafricke";

    public TodoManagerDAO(Context context){
        sharedPreferences = context.getApplicationContext().getSharedPreferences(sharedPreferencesDate, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    };

    public void saveTodoManager(TodoManager todoManager) {
        Gson gson = new Gson();
        String json = gson.toJson(todoManager);
        editor.putString(sharedPreferencesString,json);
        editor.apply();
    }

    public TodoManager readTodoManager() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(sharedPreferencesString, null);
        if (json != null) {
            Type type = new TypeToken<TodoManager>(){}.getType();
            TodoManager todoManager = gson.fromJson(json, type);
            if (todoManager != null) {
                return todoManager;
            }
        }
        return new TodoManager();
    }

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
