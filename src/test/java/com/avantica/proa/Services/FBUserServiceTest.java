package com.avantica.proa.Services;

import com.avantica.proa.Models.FBUser;
import com.avantica.proa.Security.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
class FBUserServiceTest {
    @Autowired
    private FBUserService fbUserService;

    @Test
    public void verify_fb_user_service_can_handle_unexisting_emails() {
        String email = "daenerys@is.targaryan";

        FBUser fbUser = fbUserService.findByEmail(email);

        assertNull(fbUser);
    }

    @Test
    public void verify_fb_user_service_can_insert_fbuser() throws Exception {
        FBUser fbUser = new FBUser();

        fbUser.setEmail("samantha_ezuxmjf_castillo@tfbnw.net");
        fbUser.setName("Samantha Castillo");
        fbUser.setFbtoken("EAAlVXZAWbEg0BANKmHRiyOOTX2WnupjiQvXeDX" +
                "543bhEWI0ShmYtGLHsR96DUF1Y2NEohWGBzF5oAGatMR5hKAhjM" +
                "1iqP1NWUeL9ia9M0jkCn9rcdFfUQ3BV9ZAK7sv6QZBsVvKxLatXM" +
                "tuGQVeZBK58ZAY2dCfE24CWcIKTyrWpoej33eiusgeI8EeI1ZB3M4VZCsb122wjL4NiiRaKuLR");

        FBUser resultFBUser = fbUserService.saveFBUser(fbUser);

        assertEquals(fbUser.getEmail(), resultFBUser.getEmail());
    }

    @Test
    public void verify_fb_user_service_can_find_by_email() {
        String email = "samantha_ezuxmjf_castillo@tfbnw.net";

        FBUser fbUser = fbUserService.findByEmail(email);

        assertEquals(email, fbUser.getEmail());
    }

    @Test
    public void verify_fb_user_service_can_authorize_users() {
        MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();
        FBUser fbUser = new FBUser();

        fbUser.setEmail("samantha_ezuxmjf_castillo@tfbnw.net");
        fbUser.setFbtoken("EAAlVXZAWbEg0BAGpYVUPZAd69vzhAV2JYXd7VSMzWFiMjJwZCr7zEVgIrZBVAoMT39I2HL2HhWFhZAQcVEX2Bw2ke15H49c1Ws796n8XpO3KFetkZAJ5vZCEWqOcMGSrtWlSkiasO0vcBoKrSM6SIvZCM7O9lSMukhuA1o7BuioY8gRChK8E1JW37zp6pCjmg0vw61XwJeNK28OAYtNO1X9V");

        fbUserService.loginWithFB(httpServletResponse, fbUser);

        String token = httpServletResponse.getHeader("Authorization");
        Claims decodedToken = JwtUtil.decodeJWT(token);

        String resultEmail = decodedToken.getSubject();

        assertEquals(fbUser.getEmail(), resultEmail);
    }

    @Test
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
    public void verify_fb_user_service_can_avoid_duplicate_emails() throws Exception {
        FBUser fbUser = new FBUser();

        fbUser.setEmail("samantha_ezuxmjf_castillo@tfbnw.net");
        fbUser.setName("Samantha Castillo");
        fbUser.setFbtoken("EAAlVXZAWbEg0BANKmHRiyOOTX2WnupjiQvXeDX" +
                "543bhEWI0ShmYtGLHsR96DUF1Y2NEohWGBzF5oAGatMR5hKAhjM" +
                "1iqP1NWUeL9ia9M0jkCn9rcdFfUQ3BV9ZAK7sv6QZBsVvKxLatXM" +
                "tuGQVeZBK58ZAY2dCfE24CWcIKTyrWpoej33eiusgeI8EeI1ZB3M4VZCsb122wjL4NiiRaKuLR");

        FBUser resultFBUser = fbUserService.saveFBUser(fbUser);

        assertNull(resultFBUser);
    }
}