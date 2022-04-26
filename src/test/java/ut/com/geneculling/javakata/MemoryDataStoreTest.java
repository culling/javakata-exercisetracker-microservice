package ut.com.geneculling.javakata;

import com.geneculling.javakata.api.DataStore;
import com.geneculling.javakata.api.Jsonable;
import com.geneculling.javakata.impl.MemoryDataStore;
import com.google.gson.JsonElement;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MemoryDataStoreTest {
    @Test
    public void canary(){
        assertTrue(true);
    }

    @Test
    public void canSave(){
        DataStore dataStore = new MemoryDataStore();
        dataStore.save("key", "value");
        String value = dataStore.load("key");
        assertEquals(value, "value");
    }

    @Test
    public void canLoad_value1(){
        DataStore dataStore = new MemoryDataStore(
                new HashMap<String, String>(){{
                    put("key1","value1");
                }}
        );
        String value = dataStore.load("key1");
        assertEquals(value, "value1");
    }

    @Test
    public void canLoad_value2(){
        DataStore dataStore = new MemoryDataStore(
                new HashMap<String, String>(){{
                    put("key1","value1");
                    put("key2","value2");
                }}
        );
        String value1 = dataStore.load("key1");
        assertEquals(value1, "value1");
        String value2 = dataStore.load("key2");
        assertEquals(value2, "value2");
    }

    @Test
    public void jsonable(){
        Jsonable dataStore = new MemoryDataStore(
            new HashMap<String, String>(){{
                put("key1","value1");
                put("key2","value2");
            }}
        );

        JsonElement json = dataStore.getJson();
        assertEquals("{\"key1\":\"value1\",\"key2\":\"value2\"}", json.toString());
    }

}
