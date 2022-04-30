package com.geneculling.javakata.impl;

import com.geneculling.javakata.api.DataStore;
import com.geneculling.javakata.api.Jsonable;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class MemoryDataStore implements DataStore, Jsonable {

    private final static Type LIST_STRING_TYPE = new TypeToken<ArrayList<String>>() {}.getType();
    private final static Gson GSON = new Gson();
    private final static JsonParser jsonParser = new JsonParser();
    private Map<String, String> map;

    public MemoryDataStore() {
        this.map = new HashMap<>();
    }

    public MemoryDataStore(Map<String, String> map) {
        Set<Map.Entry<String, String>> entries = map.entrySet();
        this.map = (HashMap<String, String>) entries.stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void save(String key, String value) {
        this.map.put(key, value);
    }

    @Override
    public String load(String key) {
        return map.get(key);
    }

    @Override
    public void remove(String key) {
        map.remove(key);
    }

    @Override
    public JsonElement getJson() {
        JsonObject json = new JsonObject();
        for(Map.Entry<String, String> entry: map.entrySet()){
            try {
                json.add(entry.getKey(), new JsonParser().parse(entry.getValue()).getAsJsonObject());
            } catch(IllegalStateException e) {
                json.add(entry.getKey(), GSON.toJsonTree( entry.getValue()));
            }
        }
        return json;
    }

    @Override
    public void saveToList(String listKey, String value) {
        String loadedJson = this.load(listKey);
        List<String> listJsonable = new ArrayList<>();
        if (loadedJson != null) {
            listJsonable = GSON.fromJson(loadedJson, LIST_STRING_TYPE);
        }
        listJsonable.add(value);
        String json = GSON.toJson( listJsonable );
        this.save(listKey, json);
    }

    @Override
    public String loadJsonList(String listKey) {
        String loadedJson = this.load(listKey);
        List<String> list = new ArrayList<>();
        if (loadedJson != null) {
            list = GSON.fromJson(loadedJson, LIST_STRING_TYPE);
        }
        JsonArray jsonArray = new JsonArray();
        for(String element: list){
            jsonArray.add( jsonParser.parse(element));
        }
        return jsonArray.toString();
    }
}
