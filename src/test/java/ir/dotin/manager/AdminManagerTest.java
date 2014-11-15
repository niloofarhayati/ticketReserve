package ir.dotin.manager;

import ir.dotin.Manager.AdminManager;
import ir.dotin.Model.User;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

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
        adminManager.save(user);
        assertTrue(adminManager.Login("admin", "1234"));

    }

}
