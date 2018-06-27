package com.eltrio723.recetario.database;

import com.eltrio723.recetario.Recipe;

import java.util.List;

import io.reactivex.Flowable;

public interface IRecipeDataSource {

    Flowable<Recipe> getRecipeById(int recipeID);
    Flowable<List<Recipe>> getAllRecipes();

    void insertRecipe(Recipe... recipes);

    void updateRecipe(Recipe... recipes);

    void deleteRecipe(Recipe recipe);

    void deleteAllRecipes();

}
