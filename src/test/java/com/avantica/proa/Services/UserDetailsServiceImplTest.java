package com.avantica.proa.Services;

import com.avantica.proa.Models.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserDetailsServiceImplTest {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void verify_user_service_can_save_users() {
        User user = new User();

        user.setPassword(bCryptPasswordEncoder.encode("STRONG_PASSWORD"));
        user.setEmail("example@test.com");
        user.setName("Jhon Doe");
        user.setRole((byte) 1);

        User resultUser = userDetailsService.save(user);

        assertEquals(user.getEmail(), resultUser.getEmail());
    }

    @Test
    public void verify_user_service_can_find_by_email() {
        UserDetails userDetails = userDetailsService.loadUserByUsername("example@test.com");

        assertEquals("example@test.com", userDetails.getUsername());
    }
}