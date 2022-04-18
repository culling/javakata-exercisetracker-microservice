package com.geneculling.javakata.impl;

import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.geneculling.javakata.api.DataStore;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class PluginSettingsDataStore implements DataStore {
    private PluginSettings pluginSettings;
    private String prefix;

    public PluginSettingsDataStore(PluginSettingsFactory pluginSettingsFactory, String prefix){
        this.pluginSettings = pluginSettingsFactory.createGlobalSettings();
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
}
