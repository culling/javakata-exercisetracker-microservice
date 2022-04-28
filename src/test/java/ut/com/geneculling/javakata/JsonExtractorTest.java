package ut.com.geneculling.javakata;

import com.geneculling.javakata.api.DataStore;
import com.geneculling.javakata.impl.MemoryDataStore;
import com.geneculling.javakata.pojo.User;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JsonExtractorTest {
    DataStore dataStore = new MemoryDataStore(new HashMap<String, String>() {{
        put("key", "value");
    }});


    @Test
    public void firstTest(){
        DataStore dataStore = new MemoryDataStore(new HashMap<String, String>() {{
            put("key", "value");
        }});

        String usersListKey = "users";
        String valueToAddToList = new User("JOE POTATO")
                .getJson()
                .getAsString();

        dataStore.saveToList(usersListKey, valueToAddToList);
        String json = dataStore.loadJsonList(usersListKey);
        assertTrue(json.contains("\"username\":\"JOE POTATO\""));
    }
}
