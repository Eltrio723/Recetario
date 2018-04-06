package com.eltrio723.recetario;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RecipeManager {
    private static final RecipeManager ourInstance = new RecipeManager();
    private static final String STORED_RECIPES_KEY = "STORED_RECIPES_KEY";

    private List<Recipe> recipes;
    private Context context;
    private int next_id;

    private Recipe temporalRecipe;

    public static RecipeManager getInstance() {
        return ourInstance;
    }

    private RecipeManager() {
        recipes = new ArrayList<Recipe>();
        next_id = recipes.size();
        temporalRecipe = new Recipe();
    }

    public void init(Context context){
        this.context = context;
    }


    List<Recipe> getRecipes(){
        return recipes;
    }

    void setRecipes(List<Recipe> recipes){
        this.recipes = recipes;
    }

    Recipe getRecipeOfIndex(int i){
        return recipes.get(i);
    }

    Boolean addRecipe(Recipe recipe){
        recipe.setId(next_id);
        next_id++;
        Boolean result = recipes.add(recipe);
        storeRecipes();
        return result;
    }

    void removeRecipe(Recipe r){
        recipes.remove(r);
    }

    void clear(){
        recipes.clear();
    }

    Context getContext(){
        return context;
    }

    void storeRecipes(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(recipes);
        prefsEditor.putString(STORED_RECIPES_KEY, json);
        prefsEditor.apply();
    }

    void loadRecipes(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(STORED_RECIPES_KEY,"");
        Type type = new TypeToken<List<Recipe>>(){}.getType();
        recipes = gson.fromJson(json, type);
    }

    public void storeTempRecipe(Recipe r){
        temporalRecipe = r;
    }

    public Recipe loadTempRecipe(){
        return temporalRecipe;
    }

}
