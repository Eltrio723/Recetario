package com.eltrio723.recetario.database;

import com.eltrio723.recetario.Recipe;

import java.util.List;

import io.reactivex.Flowable;

public class RecipeRepository implements IRecipeDataSource {

    private IRecipeDataSource localDataSource;
    private static RecipeRepository instance;

    public RecipeRepository(IRecipeDataSource localDataSource){
        this.localDataSource = localDataSource;
    }

    public static RecipeRepository getInstance(IRecipeDataSource localDataSource){
        if(instance == null)
            instance = new RecipeRepository(localDataSource);
        return instance;
    }

    @Override
    public Flowable<Recipe> getRecipeById(int recipeID) {
        return localDataSource.getRecipeById(recipeID);
    }

    @Override
    public Flowable<List<Recipe>> getAllRecipes() {
        return localDataSource.getAllRecipes();
    }

    @Override
    public void insertRecipe(Recipe... recipes) {
        localDataSource.insertRecipe(recipes);
    }

    @Override
    public void updateRecipe(Recipe... recipes) {
        localDataSource.updateRecipe(recipes);
    }

    @Override
    public void deleteRecipe(Recipe recipe) {
        localDataSource.deleteRecipe(recipe);
    }

    @Override
    public void deleteAllRecipes() {
        localDataSource.deleteAllRecipes();
    }
}
