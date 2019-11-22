package com.avantica.proa.Controllers;

import com.avantica.proa.Models.Topic;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TopicControllerTest {

    @Autowired
    private TopicController topicController;

    public long get_last_topic_id(){
        List<Topic> result = topicController.findAll().getBody();

        return result.get(result.size() - 1).getTopic_id();
    }

    @Test
    @Order(1)
    public void verify_topic_resource_can_save_topics() {
        Topic topic = new Topic();

        topic.setName("GraphQL");

        HttpStatus status = topicController.save(topic).getStatusCode();

        assertEquals(HttpStatus.OK, status);
    }

    @Test
    @Order(2)
    public void verify_topic_controller_can_find_all_topics() {
        List<Topic> topicList = topicController.findAll().getBody();
        List<Topic> checker = new ArrayList<>();

        String className = topicList.getClass().getSimpleName();

        assertEquals(checker.getClass().getSimpleName(), className);
    }

    @Test
    @Order(3)
    public void verify_topic_resource_can_update_topics() {
        Topic topic = new Topic();

        topic.setName("PHP");
        topic.setTopic_id(get_last_topic_id());

        HttpStatus status = topicController.update(topic).getStatusCode();

        assertEquals(HttpStatus.OK, status);
    }

    @Test
    @Order(4)
    public void verify_can_find_one_topic() throws Exception {
        Topic resultTopic = topicController.findById(get_last_topic_id()).getBody();

        assertEquals("PHP", resultTopic.getName());
    }

    @Test
    @Order(5)
    public void verify_topic_resource_can_delete() throws Exception {
        HttpStatus status = topicController.delete(get_last_topic_id()).getStatusCode();

        assertEquals(HttpStatus.OK, status);
    }
}