package com.avantica.proa.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;

import java.io.IOException;

import static org.junit.Assert.*;

class JwtUtilTest {

    @Test
    public void verify_JwtUtil_can_get_authentication_token_from_headers() {
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        httpServletRequest.addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.ey" +
                "JzdWIiOiJqdWFudmZsZXRlc0BnbWFpbC5jb20iLCJleHAiOjE1NzQxMDQ4OTh9.WAYLcn6zM4BgxA-VGyrr" +
                "xWrvQ1Xb-JOxjSKpr8NPBpiHveiIjhEsGJI0m8bhUgtzBjop-926ERoI5h8ULeeFAQ");

        Authentication authentication = JwtUtil.getAuthentication(httpServletRequest);

        assertEquals("juanvfletes@gmail.com", authentication.getName());
    }

    @Test
    public void verify_JwtUtil_can_handle_false_authentication_from_headers() {
        MockHttpServletRequest httpServletResponse = new MockHttpServletRequest();
        httpServletResponse.addHeader("Authorization", "NON_FALSE_TOKEN");

        Assertions.assertThrows(MalformedJwtException.class, () -> {
            Authentication authentication = JwtUtil.getAuthentication(httpServletResponse);
        });
    }

    @Test
    public void verify_JwtUtil_can_set_authentication_token_to_user() throws IOException {
        MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();

        JwtUtil.addAuthentication(httpServletResponse, "juanvfletes@gmail.com");
        String token = httpServletResponse.getHeader("Authorization");

        boolean isABearerToken = token.contains("Bearer ");

        assertTrue(isABearerToken);
    }

    @Test
    public void check_if_JwtUtil_can_verify_a_expired_token() {
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFudmZsZXRlc0BnbWFpbC5jb20iLCJ" +
                "leHAiOjE1NzM2NTgzMjJ9.m0WqPuX9CGzCy9SdIGo9BXs5MyJvILj3LiGbT1iE9zqf4N_bDIDE-l4F" +
                "VByUfqVRt4IZ6k48K_7F4SH4Ya29Jw";

        boolean isTokenExpired = JwtUtil.verifyExpiredToken(token);

        assertTrue(isTokenExpired);
    }

    @Test
    public void check_if_JwtUtil_can_verify_a_non_expired_token() {
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFudmZsZXRlc0BnbWFpbC5jb20iLC" +
                "JleHAiOjE1NzQ2NTg2MDV9.5BDm7RXH5qUsE94BxbHbano0Azu6l370n7i8YJuzlHQsGWx_4gHh-oV" +
                "v85zmnX6Ed07RG6t59xNtT_DQCqdyDA";

        boolean isTokenExpired = JwtUtil.verifyExpiredToken(token);

        assertFalse(isTokenExpired);
    }

    @Test
    public void check_if_JwtUtil_can_decode_a_token() {
        String email = "juanvfletes@gmail.com";

        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFudmZsZXRlc0BnbWFpbC5jb20iLC" +
                "JleHAiOjE1NzQ2NTg2MDV9.5BDm7RXH5qUsE94BxbHbano0Azu6l370n7i8YJuzlHQsGWx_4gHh-oV" +
                "v85zmnX6Ed07RG6t59xNtT_DQCqdyDA";

        Claims decodedToken = JwtUtil.decodeJWT(token);
        String tokenEmail = decodedToken.getSubject();

        assertEquals(email, tokenEmail);
    }
}