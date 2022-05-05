package com.geneculling.javakata.impl;

import com.geneculling.javakata.api.DataStore;

import javax.xml.crypto.Data;


public class DataStoreFactory {
    private static DataStore dataStore;
    private static DATASTORE_TYPE datastore_type = DATASTORE_TYPE.MEMORY;
    public enum DATASTORE_TYPE{
        MEMORY,
        PLUGIN_SETTINGS
    }


    public DataStoreFactory(){

    }

    public DataStoreFactory(DataStore dataStore){
        setDataStore(dataStore);
    }

    public DataStore getDataStore(){
        if(dataStore == null){
            dataStore = getDataStore();
        }
        return dataStore;
    }

    private void setDataStore(DataStore dataStore){
        if(this.dataStore != null){
            return;
        }
        this.dataStore = dataStore;
    }

    private DataStore createDataStore(){
        if(datastore_type == DATASTORE_TYPE.MEMORY){
            return new MemoryDataStore();
        }
        // TODO: ENABLE PLUGIN DATASTORE
//        if(datastore_type == DATASTORE_TYPE.PLUGIN_SETTINGS){
//            return new PluginSettingsDataStore();
//        }
        return new MemoryDataStore();
    }
}
