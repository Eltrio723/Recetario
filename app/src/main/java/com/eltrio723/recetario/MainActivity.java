package com.eltrio723.recetario;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private DrawerLayout drawer;
    private RecipeManager recipeManager;

    private static final String RECIPE_TO_SHOW_ID = "RECIPE_TO_SHOW_ID";

    private ListView listView;
    ArrayAdapter<Recipe> arrayAdapter;


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

        recipeManager = RecipeManager.getInstance();
        recipeManager.init(this.getApplicationContext());


        navigationView.setCheckedItem(R.id.nav_all_recipes);

        listView = (ListView) findViewById(R.id.all_recipes_list_view);

        arrayAdapter = new ArrayAdapter<Recipe>(this,
                android.R.layout.simple_list_item_1, recipeManager.getRecipeList());
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent showRecipeIntent = new Intent(view.getContext(),ShowRecipeActivity.class);
                showRecipeIntent.putExtra(RECIPE_TO_SHOW_ID, position);
                startActivityForResult(showRecipeIntent,0);

            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addRecipeIntent = new Intent(MainActivity.this,AddRecipeActivity.class);
                startActivity(addRecipeIntent);
            }
        });



        refresh();

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_add_recipe:
                Intent addRecipeIntent = new Intent(this,AddRecipeActivity.class);
                startActivity(addRecipeIntent);
                break;
            case R.id.nav_about:
                Intent aboutIntent = new Intent(this,AboutActivity.class);
                startActivity(aboutIntent);
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
        else {
            super.onBackPressed();
        }

    }

    void refresh(){
        arrayAdapter.notifyDataSetChanged();
    }



}
