package ut.com.geneculling.javakata;

import com.geneculling.javakata.api.DataStore;
import com.geneculling.javakata.impl.MemoryDataStore;
import com.geneculling.javakata.pojo.UserId;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.geneculling.javakata.impl.DataStoreUserIDUtils.*;
import static org.junit.Assert.assertEquals;

public class DataStoreUserTest {
    private final static String dataStoreKey = "userIds";

    @Test
    public void shouldGetUserIdByUsername_1(){
        DataStore dataStore = new MemoryDataStore(new HashMap<String, String>() {{
            put("key", "value");
        }});

        UserId user1 = new UserId("testuser1", "aa");
        saveUserId(dataStore, dataStoreKey, user1);

        String id = getUserIdByUsername(dataStore, dataStoreKey, "testuser1");
        assertEquals("aa", id);
    }


    @Test
    public void shouldAddUser_1() {
        DataStore dataStore = new MemoryDataStore(new HashMap<String, String>() {{
            put("key", "value");
        }});

        UserId user1 = new UserId("testuser1", "1");
        saveUserId(dataStore, dataStoreKey, user1);
        String updatedJson = dataStore.load(dataStoreKey);
        assertEquals("[{\"username\":\"testuser1\",\"_id\":\"1\"}]", updatedJson);
    }


    @Test
    public void shouldAddUser_2() {
        DataStore dataStore = new MemoryDataStore(new HashMap<String, String>() {{
            put("key", "value");
        }});

        UserId user1 = new UserId("testuser1", "1");
        UserId user2 = new UserId("testuser2", "2");

        saveUserId(dataStore, dataStoreKey, user1);
        saveUserId(dataStore, dataStoreKey, user2);

        String updatedJson = dataStore.load(dataStoreKey);
        assertEquals("[{\"username\":\"testuser1\",\"_id\":\"1\"},{\"username\":\"testuser2\",\"_id\":\"2\"}]", updatedJson);
    }

    @Test
    public void shouldRemoveUser_byId() {
        DataStore dataStore = new MemoryDataStore(new HashMap<String, String>() {{
            put("key", "value");
        }});

        UserId user1 = new UserId("testuser1", "1");
        UserId user2 = new UserId("testuser2", "2");

        saveUserId(dataStore, dataStoreKey, user1);
        saveUserId(dataStore, dataStoreKey, user2);

        removeUserIdById(dataStore, dataStoreKey , "1");
        String updatedJson = dataStore.load(dataStoreKey);
        assertEquals("[{\"username\":\"testuser2\",\"_id\":\"2\"}]", updatedJson);
    }

    @Test
    public void shouldRemoveUser_byUsername() {
        DataStore dataStore = new MemoryDataStore(new HashMap<String, String>() {{
            put("key", "value");
        }});

        UserId user1 = new UserId("testuser1", "1");
        UserId user2 = new UserId("testuser2", "2");

        saveUserId(dataStore, dataStoreKey, user1);
        saveUserId(dataStore, dataStoreKey, user2);

        String username = "testuser1";
        removeUserIdByUsername(dataStore, dataStoreKey , username);
        String updatedJson = dataStore.load(dataStoreKey);
        assertEquals("[{\"username\":\"testuser2\",\"_id\":\"2\"}]", updatedJson);
    }
}
