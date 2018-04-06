package com.eltrio723.recetario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

public class AddRecipeActivity extends AppCompatActivity {

    Button mButtonNext, mButtonCancel;
    EditText mEditTex_name, mEditText_servings;
    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);


        mButtonNext = findViewById(R.id.button_next);
        mButtonCancel = findViewById(R.id.button_cancel);

        mEditTex_name = findViewById(R.id.editText_name);
        mEditText_servings = findViewById(R.id.editText_servings);

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent all_recipes_intent = new Intent(AddRecipeActivity.this,MainActivity.class);
                startActivity(all_recipes_intent);
            }
        });

        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditTex_name = (EditText) findViewById(R.id.editText_name);
                String name = mEditTex_name.getText().toString();

                mEditText_servings = (EditText) findViewById(R.id.editText_servings);

                int servings =  0;

                try {
                    servings = Integer.parseInt(mEditText_servings.getText().toString());
                } catch(NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }

                recipe = new Recipe(name,servings);
                RecipeManager.getInstance().storeTempRecipe(recipe);

                Intent add_ingredients_intent = new Intent(AddRecipeActivity.this,AddIngredientsActivity.class);

                startActivity(add_ingredients_intent);
            }
        });

    }
}
