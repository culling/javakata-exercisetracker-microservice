package com.geneculling.javakata.api;

public interface DataStore {
    void save(String key, String value );
    String load(String key);
    void remove(String key);
}
