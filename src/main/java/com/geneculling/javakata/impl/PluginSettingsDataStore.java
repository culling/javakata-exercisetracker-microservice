package com.geneculling.javakata.impl;

import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.geneculling.javakata.api.DataStore;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class PluginSettingsDataStore implements DataStore {
    private final static Type LIST_STRING_TYPE = new TypeToken<ArrayList<String>>() {}.getType();
    private final static Gson GSON = new Gson();
    private final static JsonParser jsonParser = new JsonParser();
    private PluginSettings pluginSettings;
    private String prefix;

    public PluginSettingsDataStore(PluginSettingsFactory pluginSettingsFactory, String prefix){
        this.pluginSettings = pluginSettingsFactory.createGlobalSettings();
        this.prefix = prefix;
    }

    @Override
    public void save(String key, String value) {
        pluginSettings.put(prefix +"-"+ key, value);
    }

    @Override
    public String load(String key) {
        Object rawValue = pluginSettings.get(prefix +"-"+key);
        if(rawValue == null || !rawValue.getClass().equals(String.class)) {
            return null;
        }
        return (String)rawValue;
    }

    @Override
    public void remove(String key) {
        pluginSettings.remove(key);
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
