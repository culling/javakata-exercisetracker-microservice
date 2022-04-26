package com.geneculling.javakata.impl;

import com.geneculling.javakata.api.DataStore;
import com.geneculling.javakata.api.Jsonable;
import com.geneculling.javakata.pojo.User;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MemoryDataStore implements DataStore, Jsonable {
    private Map<String, String> map;

    public MemoryDataStore() {
        this.map = new HashMap<>();
    }
    private static final Gson GSON = new Gson();

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
}
