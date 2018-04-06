package com.eltrio723.recetario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class AddStepsActivity extends AppCompatActivity {

    private Recipe recipe;
    Button mButtonAdd, mButtonFinish, mButtonCancel;
    EditText mEditTex_step;
    private List<Step> steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_steps);

        recipe = RecipeManager.getInstance().loadTempRecipe();
        steps = new ArrayList<Step>();

        mButtonAdd = findViewById(R.id.button_add);
        mButtonCancel = findViewById(R.id.button_cancel);
        mButtonFinish = findViewById(R.id.button_finish);
        mEditTex_step = findViewById(R.id.editText_step);

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent all_recipes_intent = new Intent(AddStepsActivity.this, MainActivity.class);
                startActivity(all_recipes_intent);
            }
        });

        mButtonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recipe.setSteps(steps);
                RecipeManager.getInstance().addRecipe(recipe);
                Intent all_recipes_intent = new Intent(AddStepsActivity.this, MainActivity.class);
                startActivity(all_recipes_intent);
            }
        });

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                steps.add(new Step(steps.size()+1, mEditTex_step.getText().toString()));
                mEditTex_step.setText("");
            }
        });


    }
}
