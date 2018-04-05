package com.eltrio723.recetario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShowRecipeActivity extends AppCompatActivity {

    private static final String RECIPE_TO_SHOW_ID = "RECIPE_TO_SHOW_ID";
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recipe);

        Intent intent = getIntent();
        int index = intent.getIntExtra(RECIPE_TO_SHOW_ID,0);

        recipe = RecipeManager.getInstance().getRecipeOfIndex(index);

    }
}
