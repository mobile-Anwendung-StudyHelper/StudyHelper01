package com.nfricke.coursecrafter_selfmade;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class ModulManagerDAO {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static String sharedPreferencesDate = "Data";
    private static String sharedPreferencesString = "moduldatafricke";

    public ModulManagerDAO(Context context){
        sharedPreferences = context.getApplicationContext().getSharedPreferences(sharedPreferencesDate, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    };

    public void saveModulManager(ModulManager modulManager) {
        Gson gson = new Gson();
        String json = gson.toJson(modulManager);
        editor.putString(sharedPreferencesString,json);
        editor.apply();
    }

    public void readModulManager(ModulManager modulManager) {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(sharedPreferencesString,null);
        if (json != null) {
            Type type = new TypeToken<ModulManager>(){}.getType();
            ModulManager savedData = gson.fromJson(json, type);
            if (savedData != null) {
                modulManager.addAll(savedData);
            }
        }
    }
}
