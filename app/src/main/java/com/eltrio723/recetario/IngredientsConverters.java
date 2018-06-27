package com.eltrio723.recetario;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class IngredientsConverters {
    static Gson gson = new Gson();

    @TypeConverter
    public static List<String> stringToIngredients(String ingredients) {
        if (ingredients == null) {
            return Collections.emptyList();
        }
        Type type = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(ingredients, type);
    }

    @TypeConverter
    public static String ingredientsToString(List<String> ingredients) {
        return gson.toJson(ingredients);
    }
}

