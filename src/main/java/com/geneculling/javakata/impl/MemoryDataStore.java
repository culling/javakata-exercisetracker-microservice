package com.geneculling.javakata.impl;

import com.geneculling.javakata.api.DataStore;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MemoryDataStore implements DataStore {
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
}
