package com.eltrio723.recetario;

import android.content.Intent;
import android.os.Debug;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private final static String ALL_RECIPES_FRAGMENT_TAG = "ALL_RECIPES_FRAGMENT_TAG";
    private final static String ADD_RECIPE_FRAGMENT_TAG = "ADD_RECIPE_FRAGMENT_TAG";
    private final static String ABOUT_FRAGMENT_TAG = "ABOUT_FRAGMENT_TAG";

    private DrawerLayout drawer;
    private RecipeManager recipeManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
         drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new AllRecipesFragment(),ALL_RECIPES_FRAGMENT_TAG).commit();
            navigationView.setCheckedItem(R.id.nav_all_recipes);
        }

        recipeManager = RecipeManager.getInstance();
        recipeManager.init(this.getApplicationContext());

        //PRUEBA****************************************
        Test();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_all_recipes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AllRecipesFragment(), ALL_RECIPES_FRAGMENT_TAG).commit();
                break;
            case R.id.nav_add_recipe:
                Intent i = new Intent(this,AddRecipeActivity.class);
                startActivity(i);
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AboutFragment(), ABOUT_FRAGMENT_TAG).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed(){
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (true/*Si el fragmento actual no es all recipes*/){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new AllRecipesFragment(),ALL_RECIPES_FRAGMENT_TAG).commit();
            navigationView.setCheckedItem(R.id.nav_all_recipes);
        }
        else {
            super.onBackPressed();
    }

    }

    void Test(){
        List<String> ingredients = new ArrayList<String>();
        List<Step> steps = new ArrayList<Step>();

        recipeManager.addRecipe(new Recipe(1,"hola",2, ingredients, steps));
        recipeManager.addRecipe(new Recipe(2,"adios",2, ingredients, steps));
        List<Recipe> r = recipeManager.getRecipes();
        for(int i=0;i<r.size();i++){
            Log.d("tag",r.get(i).getName());
        }

        recipeManager.storeRecipes();
        recipeManager.clear();
        List<Recipe> rp = recipeManager.getRecipes();
        for(int i=0;i<rp.size();i++){
            Log.d("tag",rp.get(i).getName());
        }
        if(rp.isEmpty()){
            Log.d("tag","Vacio");
        }
        recipeManager.loadRecipes();
        rp = recipeManager.getRecipes();
        for(int i=0;i<rp.size();i++){
            Log.d("tag",rp.get(i).getName());
        }
        if(rp.isEmpty()){
            Log.d("tag","Vacio");
        }
    }

}
