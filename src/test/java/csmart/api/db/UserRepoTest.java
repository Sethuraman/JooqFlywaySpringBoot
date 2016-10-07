package csmart.api.db;

import csmart.api.model.Address;
import csmart.api.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by sethur on 2/10/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("ci")

public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Test
    public void shouldInsertUserInDb(){
        User user = createUser();

        userRepo.createUser(user);
        User returnedUsed = userRepo.getUser("email");

        Assert.assertEquals(returnedUsed.getPassword(), user.getPassword());
    }

    private User createUser() {
        User user = new User();
        user.setEmailid("email");
        user.setPassword("password");
        user.setPracticeName("practice");
        user.setPrimaryUser(true);
        Address address = new Address();
        address.setLine1("line1");
        address.setLine2("line2");
        address.setCity("city");
        address.setPincode("pincode");
        address.setState("state");
        user.setAddress(address);
        return user;
    }
}