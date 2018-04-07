package com.eltrio723.recetario;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ShowRecipeActivity extends AppCompatActivity {

    private static final String RECIPE_TO_SHOW_ID = "RECIPE_TO_SHOW_ID";
    private Recipe recipe;

    private Button mButtonDelete;
    private TextView mTexView_recipe_name, mTextView_servings;
    private ListView listView_ingredients, listView_steps;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_recipe);

        Intent intent = getIntent();
        int index = intent.getIntExtra(RECIPE_TO_SHOW_ID,0);

        recipe = RecipeManager.getInstance().getRecipeOfIndex(index);

        mTexView_recipe_name = findViewById(R.id.textView_recipe_name);
        mTexView_recipe_name.setText(recipe.getName());

        mTextView_servings = findViewById(R.id.textView_servings_number);
        mTextView_servings.setText(Integer.toString(recipe.getServings()));


        listView_ingredients = findViewById(R.id.listView_show_ingredients);

        ArrayAdapter<String> arrayAdapter_ingredients = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, recipe.getIngredients());
        listView_ingredients.setAdapter(arrayAdapter_ingredients);

        listView_steps = findViewById(R.id.listView_show_steps);

        ArrayAdapter<Step> arrayAdapter_steps = new ArrayAdapter<Step>(this,
                android.R.layout.simple_list_item_1, recipe.getSteps());
        listView_steps.setAdapter(arrayAdapter_steps);



        mButtonDelete = findViewById(R.id.button_delete);

        mButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ShowRecipeActivity.this);

                builder.setMessage(R.string.delete_message);
                builder.setTitle(R.string.delete_title);
                builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        delete();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                AlertDialog dialog = builder.create();

                dialog.show();

            }
        });

    }


    void delete(){
        RecipeManager.getInstance().removeRecipe(recipe);
        RecipeManager.getInstance().storeRecipes();
        Intent all_recipes_intent = new Intent(ShowRecipeActivity.this, MainActivity.class);
        startActivity(all_recipes_intent);
    }


}
