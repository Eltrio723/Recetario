package com.eltrio723.recetario;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class StepsConverters {
    static Gson gson = new Gson();

    @TypeConverter
    public static List<Step> stringToSteps(String steps) {
        if (steps == null) {
            return Collections.emptyList();
        }
        Type type = new TypeToken<List<Step>>() {}.getType();
        return gson.fromJson(steps, type);
    }

    @TypeConverter
    public static String StepsToString(List<Step> steps) {
        return gson.toJson(steps);
    }
}
