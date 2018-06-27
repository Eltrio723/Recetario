package com.eltrio723.recetario;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "recipes")
public class Recipe {

    //Data----------------------------

    @NonNull
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "servings")
    private int servings;
    @ColumnInfo(name = "ingredients")
    @TypeConverters(IngredientsConverters.class)
    private List<String> ingredients;
    @ColumnInfo(name = "steps")
    @TypeConverters(StepsConverters.class)
    private List<Step> steps;


    //Constructors--------------------

    public Recipe(){
        this.id = 0;
        this.name = "";
        this.servings = 0;
        this.ingredients = new ArrayList<String>();
        this.steps = new ArrayList<Step>();
    }

    public Recipe(int id, String name, int servings, List<String> ingredients, List<Step> steps){
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public Recipe(String name, int servings, List<String> ingredients, List<Step> steps){
        this.name = name;
        this.servings = servings;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public Recipe(String name, int servings){
        this.name = name;
        this.servings = servings;
        this.ingredients = new ArrayList<String>();
        this.steps = new ArrayList<Step>();
    }

    //Getters and setters-------------


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getServings() {
        return servings;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    //Methods-----------------------

    @Override
    public String toString(){
        return name;
    }

    public String toStringFull(){
        return name + "\nServings: " + servings + "\nIngredients:\n" + ingredients.toString() + "\nSteps:\n" + steps.toString();
    }






}
