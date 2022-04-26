package ut.com.geneculling.javakata;

import com.geneculling.javakata.api.Jsonable;
import com.geneculling.javakata.pojo.User;
import com.google.gson.JsonElement;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserTest {
    @Test
    public void createUser_username(){
        User user = new User("testUser1");
        assertEquals("testUser1", user.getUsername());
        assertNotNull(user.get_id());
    }

    @Test
    public void createUser_usernameAndId(){
        UUID uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        User user = new User("testUser1", uuid);
        String username = user.getUsername();
        assertEquals("testUser1", username);
        assertEquals(uuid.toString(), user.get_id());
    }

    @Test
    public void createUser_usernameAndId_2(){
        UUID uuid = UUID.fromString("00000000-0000-0000-0000-000000000002");
        User user = new User("testUser2", uuid);
        String username = user.getUsername();
        assertEquals("testUser2", username);
        assertEquals(uuid.toString(), user.get_id());
    }

    @Test
    public void getJson(){
        UUID uuid = UUID.fromString("00000000-0000-0000-0000-000000000002");
        User user = new User("testUser2", uuid);
        String username = user.getUsername();
        assertEquals("testUser2", username);
        assertEquals(uuid.toString(), user.get_id());

        Jsonable jsonable = (Jsonable) user;
        JsonElement json = jsonable.getJson();
        String actualString = json.getAsString();
        assertEquals("{\"username\":\"testUser2\",\"_id\":\"00000000-0000-0000-0000-000000000002\"}", actualString);
    }

}
