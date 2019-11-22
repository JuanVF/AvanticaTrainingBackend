package com.avantica.proa;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
class FBTokenUtilsTest {

    @Autowired
    private FBTokenUtils fbTokenUtils;

    @Test
    public void verify_this_class_can_detect_a_false_token() throws Exception {
        String FBToken = "A_COMPLETE_FALSE_TOKEN";

        boolean isAuthentic = fbTokenUtils.checkFBToken(FBToken);

        assertFalse(isAuthentic);
    }

    @Test
    public void verify_this_class_can_detect_a_authentic_token() throws Exception {
        String FBToken = "EAAlVXZAWbEg0BAFOUBpgvbRup7Nm1z5CyHi8Q6EhxiZB2bxFVyhAK16AyXVnycFcQNInJ1BUmd4XZA1ZBIYx5cZAekwxnt2Juc1zSjP470RrPh66gdVdjq1ZCotZCbgpUwOc5GkbIgUbL4I8I8DAZACizZCSrnNlIoCAuQY6KYzhDmq6SBFT4nPzm";

        boolean isAuthentic = fbTokenUtils.checkFBToken(FBToken);

        assertTrue(isAuthentic);
    }
}