package ir.dotin.manager;

import ir.dotin.model.City;
import ir.dotin.model.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by niloofar on 11/15/14.
 */
public class AdminManagerTest {

    @Test
    public void testAdminManager() {

        AdminManager adminManager = new AdminManager();
        User user = new User();
        user.setFirst_name("new");
        user.setPassword("1234");
        user.setUsername("admin");
        user.setType(0);
       List<User> us=  adminManager.list(User.class);
        for(User u:us)
        System.out.println(u.getUsername());
        assertNotNull(adminManager.Login("admin", "1234"));

    }

}
