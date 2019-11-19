package com.avantica.proa.Controllers;

import com.avantica.proa.Models.FBUser;
import org.junit.jupiter.api.Test;
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
class FBUserControllerTest {

    @Autowired
    private FBUserController fbUserController;

    /**
     * To PASS the test please delete this user from the table before inserting in
     * because it will try to insert a duplicate user that is the second test
     * "verify_fbcontroller_can_handle_duplicates"
     **/
    @Test
    public void verify_fbcontroller_can_save_users() throws Exception {
        FBUser fbUser = new FBUser();

        fbUser.setName("Madison Controller Tester");
        fbUser.setEmail("madison_iyrguwu_tester@tfbnw.net");
        fbUser.setFbtoken("EAAlVXZAWbEg0BALp1vETZCiYGnI6wdokIJgrYpgrvh7dOD1F1Oj7ch6x0yZAMIzPWh" +
                "G5BtyM01hzumlCZCSzzUkZBykMIMC3lWZB9SXThXNYBmWM1DPKeEXDpRa2HRvY0xsJipsCq4I6aVZ" +
                "CF2lSjK8ZCbhE4OXuHd4SZBDz7X81ZBQzr8Rc9OfNtRVKT8ZCYp4GrzXbyXBGrgqEgZDZD");

        ResponseEntity response = fbUserController.saveFBUser(fbUser);
        HttpStatus status = HttpStatus.OK;

        assertEquals(status, response.getStatusCode());
    }

    @Test
    public void verify_fbcontroller_can_handle_duplicates() throws Exception {
        FBUser fbUser = new FBUser();

        fbUser.setName("Madison Controller Tester");
        fbUser.setEmail("madison_iyrguwu_tester@tfbnw.net");
        fbUser.setFbtoken("EAAlVXZAWbEg0BALp1vETZCiYGnI6wdokIJgrYpgrvh7dOD1F1Oj7ch6x0yZAMIzPWh" +
                "G5BtyM01hzumlCZCSzzUkZBykMIMC3lWZB9SXThXNYBmWM1DPKeEXDpRa2HRvY0xsJipsCq4I6aVZ" +
                "CF2lSjK8ZCbhE4OXuHd4SZBDz7X81ZBQzr8Rc9OfNtRVKT8ZCYp4GrzXbyXBGrgqEgZDZD");

        ResponseEntity response = fbUserController.saveFBUser(fbUser);
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;

        assertEquals(status, response.getStatusCode());
    }

    @Test
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
    public void verify_fbcontroller_can_success_a_login() throws IOException {
        MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();

        FBUser fbUser = new FBUser();

        fbUser.setName("Madison Controller Tester");
        fbUser.setEmail("madison_iyrguwu_tester@tfbnw.net");
        fbUser.setFbtoken("EAAlVXZAWbEg0BAIgaCxjrS4tlO4rJ4tCQPEGQl1alk9xdvBYq" +
                "6bBotsK95PbRZCVkrUIZCrOm8axIntfsKeKMRdjWOCTz2WPd414Ugj4tZAU9" +
                "xqFcdMBD37ctqezXzvjlMZAzIt2976FdvnjNRdupR18V0JRgRACESHRrjMSZ" +
                "Alt72ZC0wOsv4iamzoWUhfwoR13VnLRXgRmQZDZD");

        fbUserController.loginWithFB(httpServletRequest, httpServletResponse, fbUser);

        int status = 200;
        int resultStatus = httpServletResponse.getStatus();

        assertEquals(status, resultStatus);
    }

    @Test
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
}