package com.avantica.proa.Services;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.avantica.proa.Models.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserDetailsServiceImplTest {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void verify_user_service_can_save_users(){
        User user = new User();

        user.setPassword(bCryptPasswordEncoder.encode("STRONG_PASSWORD"));
        user.setEmail("example@test.com");
        user.setName("Jhon Doe");
        user.setRole((byte) 1);

        User resultUser = userDetailsService.save(user);

        assertEquals(user.getEmail(),resultUser.getEmail());
    }

    @Test
    public void verify_user_service_can_detect_a_false_fb_token(){
        User user = new User();

        user.setFb_token("THIS_IS_A_COMPLETE_FALSE_TOKEN");
        user.setRole((byte) 1);
        user.setEmail("false@email.com");
        user.setPassword(bCryptPasswordEncoder.encode("WEAK_PASSWORD"));
        user.setName("False Jhon Doe");

        assertThrows(Exception.class, ()->{
           userDetailsService.saveFBUser(user);
        });
    }

    @Test
    public void verify_user_service_can_save_a_real_fb_user() throws Exception {
        User user = new User();

        user.setFb_token("EAAlVXZAWbEg0BAKQhPIFiySYWulUcrXDU9PbjvblS3JOeTdd2Hqal3MDiIgYmvymKbOjGMoxUZBjKlJJFL47vBygxBaUjZBAZAMk4a9WAJ2hmbGFvDrnqVKrdUbUkyrRoi6y4X7iOdRVKrQtto7WTPnMZAnJls6MZCjFbf0FD70g4pXACpwKez16WU3ZAkfZB2BsKbCQIsDBQ1OAJtgzwdjN");
        user.setName("Samantha Aldckiicfaic McDonaldwitz");
        user.setEmail("msgfaylhsc_1572962842@tfbnw.net");
        user.setPassword("STRONG_PASSWORD");
        user.setRole((byte) 3);

        User resultUser = userDetailsService.saveFBUser(user);

        assertEquals(user.getEmail(), resultUser.getEmail());
    }

    @Test
    public void verify_user_service_can_find_by_email(){
        UserDetails userDetails = userDetailsService.loadUserByUsername("example@test.com");

        assertEquals("example@test.com",userDetails.getUsername());
    }
}