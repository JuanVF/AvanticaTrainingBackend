package com.avantica.proa.Security;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

class JwtUtilTest {

    @Test
    public void verify_JwtUtil_can_get_authentication_token_from_headers(){
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        httpServletRequest.addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFudmZsZXRlc0BnbWFpbC5jb20iLCJleHAiOjE1NzQxMDQ4OTh9.WAYLcn6zM4BgxA-VGyrrxWrvQ1Xb-JOxjSKpr8NPBpiHveiIjhEsGJI0m8bhUgtzBjop-926ERoI5h8ULeeFAQ");

        Authentication authentication = JwtUtil.getAuthentication(httpServletRequest);

        assertEquals("juanvfletes@gmail.com",authentication.getName());
    }

    @Test
    public void verify_JwtUtil_can_set_authentication_token_to_user() throws IOException {
        MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();

        JwtUtil.addAuthentication(httpServletResponse, "juanvfletes@gmail.com");
        String token = httpServletResponse.getHeader("Authorization");

        boolean isABearerToken = token.contains("Bearer ");

        assertTrue(isABearerToken);
    }
}