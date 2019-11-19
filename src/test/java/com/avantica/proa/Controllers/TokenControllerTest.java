package com.avantica.proa.Controllers;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.avantica.proa.Security.SecurityConstants.AUTHORIZATION_HEADER;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class TokenControllerTest {
    @Autowired
    private TokenController tokenController;

    @Test
    public void verify_token_controller_can_check_a_valid_token() {
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFudmZsZXRlc0BnbWFpbC5jb20iLC" +
                "JleHAiOjE1NzQ2NTg2MDV9.5BDm7RXH5qUsE94BxbHbano0Azu6l370n7i8YJuzlHQsGWx_4gHh-oV" +
                "v85zmnX6Ed07RG6t59xNtT_DQCqdyDA";

        httpServletRequest.addHeader(AUTHORIZATION_HEADER, token);

        ResponseEntity response = tokenController.verifyToken(httpServletRequest);

        HttpStatus status = HttpStatus.OK;
        HttpStatus resultStatus = response.getStatusCode();

        assertEquals(status, resultStatus);
    }

    @Test
    public void verify_token_controller_can_handle_a_expired_token() {
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFudmZsZXRlc0BnbWFpbC5jb20iLCJ" +
                "leHAiOjE1NzM2NTgzMjJ9.m0WqPuX9CGzCy9SdIGo9BXs5MyJvILj3LiGbT1iE9zqf4N_bDIDE-l4F" +
                "VByUfqVRt4IZ6k48K_7F4SH4Ya29Jw";

        httpServletRequest.addHeader(AUTHORIZATION_HEADER, token);

        ResponseEntity response = tokenController.verifyToken(httpServletRequest);

        HttpStatus resultStatus = response.getStatusCode();
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;

        assertEquals(status, resultStatus);
    }
}