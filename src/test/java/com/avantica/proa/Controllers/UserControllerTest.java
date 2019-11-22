package com.avantica.proa.Controllers;

import com.avantica.proa.Models.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {

    @Autowired
    private UserController userController;

    private String name = "The real Jhon Doe";
    private String pwd = "Jhon Doe is the best";
    private byte role = (byte) 3;
    private String email = "jhon@doe.best";

    @Test
    @Order(1)
    public void verify_user_controller_can_save_users() {
        User user = new User();

        user.setName(name);
        user.setPassword(pwd);
        user.setRole(role);
        user.setEmail(email);

        HttpStatus status = userController.save(user).getStatusCode();

        assertEquals(HttpStatus.OK, status);
    }

    @Test
    @Order(2)
    public void verify_user_controller_can_handle_repeated_user() {
        User user = new User();

        user.setName(name);
        user.setPassword(pwd);
        user.setRole(role);
        user.setEmail(email);

        HttpStatus status = userController.save(user).getStatusCode();

        assertEquals(HttpStatus.NOT_ACCEPTABLE, status);
    }

    @Test
    @Order(3)
    public void verify_user_controller_can_delete_user() throws Exception {
        userController.delete("example@test.com");
    }
}