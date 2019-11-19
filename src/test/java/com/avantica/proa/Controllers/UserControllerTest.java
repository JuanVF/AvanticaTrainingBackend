package com.avantica.proa.Controllers;

import com.avantica.proa.Models.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    public void verify_user_controller_can_handle_repeated_user() {
        User user = new User();

        user.setName("Jhon Fake Doe");
        user.setEmail("example@test.com");
        user.setRole((byte) 3);
        user.setPassword("FAKE JHON DOE WAS HERE");

        HttpStatus status = userController.save(user).getStatusCode();

        assertEquals(HttpStatus.NOT_ACCEPTABLE, status);
    }

    @Test
    public void verify_user_controller_can_save_users() {
        User user = new User();

        user.setName("The real Jhon Doe");
        user.setPassword("Jhon Doe is the best");
        user.setRole((byte) 3);
        user.setEmail("jhon@doe.best");

        HttpStatus status = userController.save(user).getStatusCode();

        assertEquals(HttpStatus.OK, status);
    }
}