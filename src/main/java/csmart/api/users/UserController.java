package csmart.api.users;

import csmart.api.db.UserRepo;
import csmart.api.model.Address;
import csmart.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sethur on 1/10/2016.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/create")
    public void createUser(@RequestBody User user){
        userRepo.createUser(user);
    }

    @GetMapping("/getUser")
    public @ResponseBody User getUser(){
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
