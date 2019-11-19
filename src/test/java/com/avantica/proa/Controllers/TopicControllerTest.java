package com.avantica.proa.Controllers;

import com.avantica.proa.Models.Topic;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class TopicControllerTest {

    @Autowired
    private TopicController topicController;

    @Test
    public void verify_topic_controller_can_find_all_topics() {
        List<Topic> topicList = topicController.findAll().getBody();
        List<Topic> checker = new ArrayList<>();

        String className = topicList.getClass().getSimpleName();

        assertEquals(checker.getClass().getSimpleName(), className);
    }

    @Test
    public void verify_can_find_one_topic() throws Exception {
        Topic resultTopic = topicController.findById(1).getBody();

        assertEquals("Bootstrap", resultTopic.getName());
    }

    @Test
    public void verify_topic_resource_can_save_topics() {
        Topic topic = new Topic();

        topic.setName("GraphQL");

        HttpStatus status = topicController.save(topic).getStatusCode();

        assertEquals(HttpStatus.OK, status);
    }

    @Test
    public void verify_topic_resource_can_update_topics() {
        Topic topic = new Topic();

        topic.setName("PHP");
        topic.setTopic_id(2);

        HttpStatus status = topicController.update(topic).getStatusCode();

        assertEquals(HttpStatus.OK, status);
    }

    @Test
    public void verify_topic_resource_can_delete() throws Exception {
        List<Topic> topicList = topicController.findAll().getBody();
        long lastID = topicList.get(topicList.size() - 1).getTopic_id();

        HttpStatus status = topicController.delete(lastID).getStatusCode();

        assertEquals(HttpStatus.OK, status);
    }
}