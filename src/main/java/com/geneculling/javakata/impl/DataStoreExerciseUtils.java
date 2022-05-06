package com.geneculling.javakata.impl;

import com.geneculling.javakata.api.DataStore;
import com.geneculling.javakata.pojo.Exercise;
import com.geneculling.javakata.pojo.UserId;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataStoreExerciseUtils {
    private final static Gson GSON = new Gson();
    private final static Type EXERCISE_LIST_CLASS_TOKEN = new TypeToken<List<Exercise>>(){}.getType();


    /**************************
     * PUBLIC METHODS
     **************************/

    public static void saveExercise(DataStore dataStore, String dataStoreKey, Exercise exercise) {
        List<Exercise> list = getExercises(dataStore, dataStoreKey);
        list.add(exercise);
        String updatedJson = GSON.toJson(list);
        dataStore.save(dataStoreKey, updatedJson);
    }

    public static List<Exercise> getExercisesById(DataStore dataStore, String dataStoreKey, String id){
        List<Exercise> list = getExercises(dataStore, dataStoreKey);
        List<Exercise> filteredList = list.stream()
                .filter(exercise -> exercise.getId().equals(id))
                .collect(Collectors.toList());
        return filteredList;
    }


    /**************************
     * PRIVATE METHODS
     **************************/

    private static List<Exercise> getExercises(DataStore dataStore, String dataStoreKey) {
        String loadedJson = dataStore.load(dataStoreKey);
        List<Exercise> list = new ArrayList<>();
        if (loadedJson != null) {
            list = GSON.fromJson(loadedJson, EXERCISE_LIST_CLASS_TOKEN);
        }
        return list;
    }

}
