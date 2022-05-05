package com.geneculling.javakata.impl;

import com.geneculling.javakata.api.DataStore;
import com.geneculling.javakata.pojo.UserId;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataStoreUserIDUtils {
    private final static Gson GSON = new Gson();
    private final static Type USER_LIST_CLASS_TOKEN = new TypeToken<List<UserId>>(){}.getType();


    /**************************
     * PUBLIC METHODS
     **************************/

    public static void saveUserId(DataStore dataStore, String dataStoreKey, UserId userId) {
        List<UserId> list = getUserIds(dataStore, dataStoreKey);
        list.add(userId);
        String updatedJson = GSON.toJson(list);
        dataStore.save(dataStoreKey, updatedJson);
    }

    public static void removeUserIdByUsername(DataStore dataStore, String dataStoreKey, String username) {
        UserId user = getUserIdByUsername(dataStore, dataStoreKey, username);
        if(user == null){
            return;
        }
        if(user.get_id() == null){
            return;
        }
        removeUserIdById(dataStore, dataStoreKey, user.get_id());
    }

    public static void removeUserIdById(DataStore dataStore, String dataStoreKey, String id) {
        List<UserId> list = getUserIds(dataStore, dataStoreKey);
        List<UserId> filteredList = list.stream()
                .filter(user -> !user.get_id().equals(id))
                .collect(Collectors.toList());
        String updatedJson = GSON.toJson(filteredList);
        dataStore.save(dataStoreKey, updatedJson);
    }

    public static UserId getUserIdByUsername(DataStore dataStore, String dataStoreKey, String username){
        List<UserId> list = getUserIds(dataStore, dataStoreKey);
        List<UserId> filteredList = list.stream()
                .filter(user -> user.getUsername().equals(username))
                .collect(Collectors.toList());
        if(filteredList.isEmpty()){
            return null;
        }
        UserId user = filteredList.get(0);
        return user;
    }

    public static UserId getUserIdById(DataStore dataStore, String dataStoreKey, String id){
        List<UserId> list = getUserIds(dataStore, dataStoreKey);
        List<UserId> filteredList = list.stream()
                .filter(user -> user.get_id().equals(id))
                .collect(Collectors.toList());
        if(filteredList.isEmpty()){
            return null;
        }
        UserId user = filteredList.get(0);
        return user;
    }

    /**************************
     * PRIVATE METHODS
     **************************/

    private static List<UserId> getUserIds(DataStore dataStore, String dataStoreKey) {
        String loadedJson = dataStore.load(dataStoreKey);
        List<UserId> list = new ArrayList<>();
        if (loadedJson != null) {
            list = GSON.fromJson(loadedJson, USER_LIST_CLASS_TOKEN);
        }
        return list;
    }

}
