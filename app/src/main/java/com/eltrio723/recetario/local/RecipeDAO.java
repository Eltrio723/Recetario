package com.eltrio723.recetario.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.eltrio723.recetario.Recipe;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface RecipeDAO {

    @Query("SELECT * FROM recipes WHERE id=:recipeID")
    Flowable<Recipe> getRecipeById(int recipeID);

    @Query("SELECT * FROM recipes")
    Flowable<List<Recipe>> getAllRecipes();

    @Insert
    void insertRecipe(Recipe... recipes);

    @Update
    void updateRecipe(Recipe... recipes);

    @Delete
    void deleteRecipe(Recipe recipe);

    @Query("DELETE FROM recipes")
    void deleteAllRecipes();

}
