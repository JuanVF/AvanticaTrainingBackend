package com.avantica.proa.Services;

import com.avantica.proa.Controllers.FBUserController;
import com.avantica.proa.Models.FBUser;
import com.avantica.proa.Security.JwtUtil;
import io.jsonwebtoken.Claims;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class FBUserServiceTest {
    @Autowired
    private FBUserService fbUserService;

    @Autowired
    private FBUserController fbUserController;

    @Test
    @Order(1)
    public void verify_fb_user_service_can_handle_unexisting_emails() {
        String email = "daenerys@is.targaryan";

        FBUser fbUser = fbUserService.findByEmail(email);

        assertNull(fbUser);
    }

    @Test
    @Order(2)
    public void verify_fb_user_service_can_insert_fbuser() throws Exception {
        FBUser fbUser = new FBUser();

        fbUser.setEmail("samantha_ezuxmjf_castillo@tfbnw.net");
        fbUser.setName("Samantha Castillo");
        fbUser.setFbtoken("EAAlVXZAWbEg0BAFOUBpgvbRup7Nm1z5CyHi8Q6EhxiZB2bxFVyhAK16AyXVnycFcQNInJ1BUmd4XZA1ZBIYx5cZAekwxnt2Juc1zSjP470RrPh66gdVdjq1ZCotZCbgpUwOc5GkbIgUbL4I8I8DAZACizZCSrnNlIoCAuQY6KYzhDmq6SBFT4nPzm");

        FBUser resultFBUser = fbUserService.saveFBUser(fbUser);

        assertEquals(fbUser.getEmail(), resultFBUser.getEmail());
    }

    @Test
    @Order(3)
    public void verify_fb_user_service_can_find_by_email() {
        String email = "samantha_ezuxmjf_castillo@tfbnw.net";

        FBUser fbUser = fbUserService.findByEmail(email);

        assertEquals(email, fbUser.getEmail());
    }

    @Test
    @Order(4)
    public void verify_fb_user_service_can_authorize_users() {
        MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();
        FBUser fbUser = new FBUser();

        fbUser.setEmail("samantha_ezuxmjf_castillo@tfbnw.net");
        fbUser.setFbtoken("EAAlVXZAWbEg0BAFOUBpgvbRup7Nm1z5CyHi8Q6EhxiZB2bxFVyhAK16AyXVnycFcQNInJ1BUmd4XZA1ZBIYx5cZAekwxnt2Juc1zSjP470RrPh66gdVdjq1ZCotZCbgpUwOc5GkbIgUbL4I8I8DAZACizZCSrnNlIoCAuQY6KYzhDmq6SBFT4nPzm");

        fbUserService.loginWithFB(httpServletResponse, fbUser);

        String token = httpServletResponse.getHeader("Authorization");
        Claims decodedToken = JwtUtil.decodeJWT(token);

        String resultEmail = decodedToken.getSubject();

        assertEquals(fbUser.getEmail(), resultEmail);
    }

    @Test
    @Order(5)
    public void verify_fb_user_service_can_handle_a_fake_authorization() {
        MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();
        FBUser fbUser = new FBUser();

        fbUser.setEmail("samantha_ezuxmjf_castillo@tfbnw.net");
        fbUser.setFbtoken("FAKE_TOKEN");

        fbUserService.loginWithFB(httpServletResponse, fbUser);

        int status = httpServletResponse.getStatus();

        assertEquals(406, status);
    }

    @Test
    @Order(6)
    public void verify_fb_user_service_can_avoid_duplicate_emails() throws Exception {
        FBUser fbUser = new FBUser();

        fbUser.setEmail("samantha_ezuxmjf_castillo@tfbnw.net");
        fbUser.setName("Samantha Castillo");
        fbUser.setFbtoken("EAAlVXZAWbEg0BAFOUBpgvbRup7Nm1z5CyHi8Q6EhxiZB2bxFVyhAK16AyXVnycFcQNInJ1BUmd4XZA1ZBIYx5cZAekwxnt2Juc1zSjP470RrPh66gdVdjq1ZCotZCbgpUwOc5GkbIgUbL4I8I8DAZACizZCSrnNlIoCAuQY6KYzhDmq6SBFT4nPzm");

        FBUser resultFBUser = fbUserService.saveFBUser(fbUser);

        assertNull(resultFBUser);
    }

    @Test
    @Order(7)
    public void delete_the_user_was_inserted() throws Exception {
        ResponseEntity res = fbUserController.delete("samantha_ezuxmjf_castillo@tfbnw.net");

        assertEquals(HttpStatus.OK, res.getStatusCode());
    }
}