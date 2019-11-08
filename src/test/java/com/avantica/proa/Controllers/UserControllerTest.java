package com.avantica.proa.Controllers;

import static org.junit.Assert.*;

import com.avantica.proa.Models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    public void verify_user_controller_can_handle_fake_fb_user() throws Exception {
        User user = new User();

        user.setPassword("A REALLY STRONG PASSWORD");
        user.setRole((byte) 1);
        user.setEmail("fake@jhon-doe.com");
        user.setFb_token("A_FAKE_FB_TOKEN");

        HttpStatus status = userController.saveFBUser(user).getStatusCode();

        assertEquals(HttpStatus.NOT_ACCEPTABLE, status);
    }

    @Test
    public void verify_user_controller_can_save_fb_user() throws Exception {
        User user = new User();

        user.setPassword("STRONG_PASSWORD");
        user.setRole((byte) 1);
        user.setFb_token("EAAlVXZAWbEg0BAA3ygpNTTLPXdZBffwZACHZAdZBySOteAE38soP3mvgMOifMZBIs6zGdTp9uOYnD0dZCtZCNu9EZAAxWYZBmowYqk6rjmvtIhuhmYqv1pU9tDxxClRduRv3f2zPZAMWyWHUSCZA9xLlEB0N0OpI8xP5DXg7BE6yWJVLIyn5Dm4FqVUTGFcc17g5g3A0nvDz0YdRD4wdRlD4qfd5");
        user.setName("Open Graph Test User");
        user.setEmail("open_tcmmsuo_user@tfbnw.net");

        HttpStatus status = userController.saveFBUser(user).getStatusCode();

        assertEquals(HttpStatus.OK, status);
    }

    @Test
    public void verify_user_controller_can_handle_repeated_fb_users() throws Exception{
        User user = new User();

        user.setFb_token("EAAlVXZAWbEg0BAKQhPIFiySYWulUcrXDU9PbjvblS3JOeTdd2Hqal3MDiIgYmvymKbOjGMoxUZBjKlJJFL47vBygxBaUjZBAZAMk4a9WAJ2hmbGFvDrnqVKrdUbUkyrRoi6y4X7iOdRVKrQtto7WTPnMZAnJls6MZCjFbf0FD70g4pXACpwKez16WU3ZAkfZB2BsKbCQIsDBQ1OAJtgzwdjN");
        user.setName("Samantha Aldckiicfaic McDonaldwitz");
        user.setEmail("msgfaylhsc_1572962842@tfbnw.net");
        user.setPassword("STRONG_PASSWORD");
        user.setRole((byte) 3);

        HttpStatus status = userController.saveFBUser(user).getStatusCode();

        assertEquals(HttpStatus.NOT_ACCEPTABLE,status);
    }

    @Test
    public void verify_user_controller_can_handle_repeated_user(){
        User user = new User();

        user.setName("Jhon Fake Doe");
        user.setEmail("example@test.com");
        user.setRole((byte) 3);
        user.setPassword("FAKE JHON DOE WAS HERE");

        HttpStatus status = userController.save(user).getStatusCode();

        assertEquals(HttpStatus.NOT_ACCEPTABLE, status);
    }

    @Test
    public void verify_user_controller_can_save_users(){
        User user = new User();

        user.setName("The real Jhon Doe");
        user.setPassword("Jhon Doe is the best");
        user.setRole((byte) 3);
        user.setEmail("jhon@doe.best");

        HttpStatus status = userController.save(user).getStatusCode();

        assertEquals(HttpStatus.OK, status);
    }
}