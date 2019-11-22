package com.avantica.proa.Services;

import com.avantica.proa.Controllers.UserController;
import com.avantica.proa.Models.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDetailsServiceImplTest {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserController userController;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private String email = "example@test.com";
    private String name = "Jhon Doe";
    private String password = "STRONG_PASSWORD";
    private byte role = (byte) 1;
    @Test
    @Order(1)
    public void verify_user_service_can_save_users() {
        User user = new User();

        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setEmail(email);
        user.setName(name);
        user.setRole(role);

        User resultUser = userDetailsService.save(user);

        assertEquals(user.getEmail(), resultUser.getEmail());
    }

    @Test
    @Order(2)
    public void verify_user_service_can_find_by_email() {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        assertEquals(email, userDetails.getUsername());
    }

    @Test
    @Order(3)
    public void verify_user_service_can_delete_user_by_email() throws Exception {
        userController.delete(email);
    }
}