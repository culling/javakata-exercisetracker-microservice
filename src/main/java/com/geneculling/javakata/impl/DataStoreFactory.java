package com.geneculling.javakata.impl;

import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.geneculling.javakata.api.DataStore;


public class DataStoreFactory {
    private static DataStore dataStore;
    private static DATASTORE_TYPE datastoreType = DATASTORE_TYPE.MEMORY;
    private PluginSettingsFactory pluginSettingsFactory;

    public enum DATASTORE_TYPE{
        MEMORY,
        PLUGIN_SETTINGS
    }

    /** Constructors */
    public DataStoreFactory(PluginSettingsFactory pluginSettingsFactory){
        this.pluginSettingsFactory = pluginSettingsFactory;
        this.dataStore = getDataStore();
    }
    public DataStoreFactory(PluginSettingsFactory pluginSettingsFactory, DATASTORE_TYPE datastore_type){
        this.pluginSettingsFactory = pluginSettingsFactory;
        this.datastoreType = datastore_type;
        this.dataStore = getDataStore(datastore_type);
    }

    /** Public Methods */
    public DataStore getDataStore(){
        return getDataStore(datastoreType);
    }

    public DataStore getDataStore(DATASTORE_TYPE datastore_type){
        if(dataStore == null){
            dataStore = newDataStore(datastore_type);
        }
        return dataStore;
    }
    public void setDataStore(DataStore dataStore){
        this.dataStore = dataStore;
    }

    /** Private Methods */
    private DataStore newDataStore(DATASTORE_TYPE datastore_type){
        switch(datastore_type){
            case MEMORY:
                return new MemoryDataStore();
            case PLUGIN_SETTINGS:
                return new PluginSettingsDataStore(pluginSettingsFactory, "com.geneculling.javakata.exercisetracker-microservice");
            default:
                return new MemoryDataStore();
        }
    }
}
