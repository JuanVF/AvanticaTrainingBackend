package com.avantica.proa.Controllers;

import com.avantica.proa.Models.FBUser;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FBUserControllerTest {

    @Autowired
    private FBUserController fbUserController;

    @Test
    @Order(1)
    public void verify_fbcontroller_can_save_users() throws Exception {
        FBUser fbUser = new FBUser();

        fbUser.setName("Madison Controller Tester");
        fbUser.setEmail("madison_iyrguwu_tester@tfbnw.net");
        fbUser.setFbtoken("EAAlVXZAWbEg0BAM64g3l9rCgD2KilWIRSeNH8533r3r8JwUMfZBp0YG2GgW" +
                "8X8NINw5PZAbAkNgipUxqt6h7iSoAPBWBBcMFB94poCqVrgGfDOB2nLJ8nxdot4o9AiDck" +
                "9qxQzk8xjiGiDHyFS9njRzDv0hZAZAhqIIzPDkckJL9ActjgGqX0");

        ResponseEntity response = fbUserController.saveFBUser(fbUser);
        HttpStatus status = HttpStatus.OK;

        assertEquals(status, response.getStatusCode());
    }

    @Test
    @Order(2)
    public void verify_fbcontroller_can_handle_duplicates() throws Exception {
        FBUser fbUser = new FBUser();

        fbUser.setName("Madison Controller Tester");
        fbUser.setEmail("madison_iyrguwu_tester@tfbnw.net");
        fbUser.setFbtoken("EAAlVXZAWbEg0BAM64g3l9rCgD2KilWIRSeNH8533r3r8JwUMfZBp0YG2GgW8X8N" +
                "INw5PZAbAkNgipUxqt6h7iSoAPBWBBcMFB94poCqVrgGfDOB2nLJ8nxdot4o9AiDck9qxQzk8x" +
                "jiGiDHyFS9njRzDv0hZAZAhqIIzPDkckJL9ActjgGqX0");

        ResponseEntity response = fbUserController.saveFBUser(fbUser);
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;

        assertEquals(status, response.getStatusCode());
    }

    @Test
    @Order(3)
    public void verify_fbcontroller_can_handle_a_non_acceptable_body() throws Exception {
        FBUser nonAcceptableFBUser = new FBUser();

        nonAcceptableFBUser.setEmail("a_false_email@fake.org");
        nonAcceptableFBUser.setName("Anita Fake");
        nonAcceptableFBUser.setFbtoken("FAKE Token");

        ResponseEntity response = fbUserController.saveFBUser(nonAcceptableFBUser);
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;

        assertEquals(status, response.getStatusCode());
    }

    @Test
    @Order(4)
    public void verify_fbcontroller_login_can_handle_a_false_token() throws IOException {
        MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        FBUser nonAcceptableFBUser = new FBUser();

        nonAcceptableFBUser.setFbtoken("FALSE_TOKEN");
        nonAcceptableFBUser.setName("Madison Controller Tester");
        nonAcceptableFBUser.setEmail("madison_iyrguwu_tester@tfbnw.net");

        fbUserController.loginWithFB(httpServletRequest,
                httpServletResponse,
                nonAcceptableFBUser);

        int status = 406;
        int resultStatus = httpServletResponse.getStatus();

        assertEquals(status, resultStatus);
    }

    @Test
    @Order(5)
    public void verify_fbcontroller_can_success_a_login() throws IOException {
        MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();

        FBUser fbUser = new FBUser();

        fbUser.setName("Madison Controller Tester");
        fbUser.setEmail("madison_iyrguwu_tester@tfbnw.net");
        fbUser.setFbtoken("EAAlVXZAWbEg0BAM64g3l9rCgD2KilWIRSeNH8533r3r8JwUMfZBp0YG2GgW8X8NINw5PZ" +
                "AbAkNgipUxqt6h7iSoAPBWBBcMFB94poCqVrgGfDOB2nLJ8nxdot4o9AiDck9qxQzk8xjiGiDHyFS9nj" +
                "RzDv0hZAZAhqIIzPDkckJL9ActjgGqX0");

        fbUserController.loginWithFB(httpServletRequest, httpServletResponse, fbUser);

        int status = 200;
        int resultStatus = httpServletResponse.getStatus();

        assertEquals(status, resultStatus);
    }

    @Test
    @Order(6)
    public void verify_fbcontroller_login_can_handle_false_user() throws IOException {
        MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        FBUser fbUser = new FBUser();

        fbUser.setEmail("anita@is.fake");
        fbUser.setFbtoken("FALSE_TOKEN");
        fbUser.setName("Anita Fake");

        fbUserController.loginWithFB(httpServletRequest,
                httpServletResponse,
                fbUser);

        int status = 406;
        int resultStatus = httpServletResponse.getStatus();

        assertEquals(status, resultStatus);
    }

    @Test
    @Order(7)
    public void verify_fbcontroller_cand_delete_users() throws Exception {
        fbUserController.delete("madison_iyrguwu_tester@tfbnw.net");
    }

    @Test
    @Order(8)
    public void verify_if_it_can_handle_fake_users() throws Exception {
        fbUserController.delete("fake@jhon.net");
    }
}