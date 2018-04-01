package com.eltrio723.recetario;
import java.util.List;

public class Recipe {

    //Data----------------------------

    private int id;
    private String name;
    private int servings;
    private List<String> ingredients;
    private List<Step> steps;


    //Constructors--------------------

    public Recipe(int id, String name, int servings, List<String> ingredients, List<Step> steps){
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.ingredients = ingredients;
        this.steps = steps;
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



}
