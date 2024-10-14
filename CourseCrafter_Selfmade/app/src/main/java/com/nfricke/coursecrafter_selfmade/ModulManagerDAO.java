package com.nfricke.coursecrafter_selfmade;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class ModulManagerDAO {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public ModulManagerDAO(Context context){
        sharedPreferences = context.getApplicationContext().getSharedPreferences("Data", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    };

    public void saveModulManager(ModulManager modulManager) {
        Gson gson = new Gson();
        String json = gson.toJson(modulManager);
        editor.putString("moduldatafricke",json);
        editor.apply();
    }

    public ModulManager readModulManager() {
        ModulManager modulManager = new ModulManager();
        Gson gson = new Gson();
        String json = sharedPreferences.getString("moduldatafricke",null);
        Type type = new TypeToken<ModulManager>(){}.getType();
        modulManager = gson.fromJson(json,type);
        return modulManager;
    }

    public void readModulManager(ModulManager modulManager) {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("moduldatafricke",null);
        Type type = new TypeToken<ModulManager>(){}.getType();
        modulManager.addAll(gson.fromJson(json,type));
    }
}
