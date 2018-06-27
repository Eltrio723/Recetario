package com.eltrio723.recetario.local;

import com.eltrio723.recetario.database.IRecipeDataSource;
import com.eltrio723.recetario.Recipe;

import java.util.List;

import io.reactivex.Flowable;

public class RecipeDataSource implements IRecipeDataSource {

    private RecipeDAO recipeDAO;
    private static RecipeDataSource instance;

    public RecipeDataSource(RecipeDAO recipeDAO){
        this.recipeDAO = recipeDAO;
    }

    public static  RecipeDataSource getInstance(RecipeDAO recipeDAO){
        if(instance == null){
            instance = new RecipeDataSource(recipeDAO);
        }
        return instance;
    }

    @Override
    public Flowable<Recipe> getRecipeById(int recipeID) {
        return recipeDAO.getRecipeById(recipeID);
    }

    @Override
    public Flowable<List<Recipe>> getAllRecipes() {
        return recipeDAO.getAllRecipes();
    }

    @Override
    public void insertRecipe(Recipe... recipes) {
        recipeDAO.insertRecipe(recipes);
    }

    @Override
    public void updateRecipe(Recipe... recipes) {
        recipeDAO.updateRecipe(recipes);
    }

    @Override
    public void deleteRecipe(Recipe recipe) {
        recipeDAO.deleteRecipe(recipe);
    }

    @Override
    public void deleteAllRecipes() {
        recipeDAO.deleteAllRecipes();
    }
}
