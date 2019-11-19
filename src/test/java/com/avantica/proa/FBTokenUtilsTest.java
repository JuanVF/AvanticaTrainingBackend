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
        String FBToken = "EAAlVXZAWbEg0BAPNQVuZAgp6LzaXtyuY6Y1NZCufI5rIMHZBRVgIChffjiPVPf6EKzxD793nqhDOL4gbeiUKVZC6ZBLQDyZCYX2RQBaLqZCLJR46Q2TE1tvB6XnrK1wBFNzZAZCA2jZCTmZBP1tLAsIxV1B7KrZCijmrFpt81oLWT9mbWGKORbtrraFIGlaQdBD4rcr6PUewFKY1I3qTS5s2qCkZAd";

        boolean isAuthentic = fbTokenUtils.checkFBToken(FBToken);

        assertTrue(isAuthentic);
    }
}