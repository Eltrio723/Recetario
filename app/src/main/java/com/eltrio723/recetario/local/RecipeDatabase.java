package com.eltrio723.recetario.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.eltrio723.recetario.Recipe;

import static com.eltrio723.recetario.local.RecipeDatabase.DATABASE_VERSION;

@Database(entities = Recipe.class, version = DATABASE_VERSION)
public abstract class RecipeDatabase extends RoomDatabase{
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="Recipe-Database-Room";

    public abstract RecipeDAO recipeDAO();

    private static RecipeDatabase instance;

    public static RecipeDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context,RecipeDatabase.class,DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


}
