package com.eltrio723.recetario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Recipe {

    //Data----------------------------

    private int id;
    private String name;
    private int servings;
    private List<String> ingredients;
    private List<Step> steps;


    //Constructors--------------------

    public Recipe(){
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

    public void copy(Recipe r){
        this.id = r.id;
        this.name = r.name;
        this.servings = r.servings;
        this.ingredients = r.ingredients;
        this.steps = r.steps;
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void fromJson(String json){
        Recipe aux;
        Gson gson = new Gson();
        Type type = new TypeToken<List<Recipe>>(){}.getType();
        //aux = gson.fromJson(json, type);
        //this.copy(aux);
    }





}
