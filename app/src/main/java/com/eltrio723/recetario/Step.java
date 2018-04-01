package com.eltrio723.recetario;

public class Step {

    //Data----------------------------

    private int id;
    private String description;

    //Constructors--------------------

    public Step(int id, String description){
        this.id = id;
        this.description = description;
    }

    //Getters and setters-------------


    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
