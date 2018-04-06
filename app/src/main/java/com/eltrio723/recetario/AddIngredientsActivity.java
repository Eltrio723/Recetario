package com.eltrio723.recetario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AddIngredientsActivity extends AppCompatActivity {

    private Recipe recipe;
    Button mButtonAdd, mButtonNext, mButtonCancel;
    EditText mEditTex_ingredient;
    private List<String> ingredients;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredients);

        ingredients = new ArrayList<String>();

        listView = findViewById(R.id.listView_ingredients);

        recipe = RecipeManager.getInstance().loadTempRecipe();

        mEditTex_ingredient = (EditText) findViewById(R.id.editText_ingredient);

        mButtonAdd = findViewById(R.id.button_add);
        mButtonNext = findViewById(R.id.button_next);
        mButtonCancel = findViewById(R.id.button_cancel);

        refresh();

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addIngredient(mEditTex_ingredient.getText().toString());
            }
        });

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent all_recipes_intent = new Intent(AddIngredientsActivity.this,MainActivity.class);
                startActivity(all_recipes_intent);
            }
        });

        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recipe.setIngredients(ingredients);
                RecipeManager.getInstance().storeTempRecipe(recipe);
                Intent add_steps_intent = new Intent(AddIngredientsActivity.this,AddStepsActivity.class);
                startActivity(add_steps_intent);
            }
        });

    }


    public void addIngredient(String ing){
        ingredients.add(ing);
        refresh();
    }

    void refresh(){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, ingredients);
        listView.setAdapter(arrayAdapter);
        mEditTex_ingredient.setText("");
    }

}
