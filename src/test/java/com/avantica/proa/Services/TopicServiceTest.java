package com.avantica.proa.Services;

import com.avantica.proa.Models.Topic;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TopicServiceTest {
    @Autowired
    private TopicService topicService;

    public long get_inserted_topic_id(){
        List<Topic> result = topicService.findAll();

        return result.get(result.size() - 1).getTopic_id();
    }

    @Test
    @Order(1)
    public void verify_topic_service_can_save_topics() {
        Topic topic = new Topic();

        topic.setName("Node.js");

        Topic resultTopic = topicService.save(topic);

        assertEquals("Node.js", resultTopic.getName());
    }

    @Test
    @Order(2)
    public void verify_topic_service_can_update_topics() {
        Topic topic = new Topic();

        topic.setName("Bootstrap");
        topic.setTopic_id(get_inserted_topic_id());

        Topic resultTopic = topicService.update(topic);

        assertEquals(get_inserted_topic_id(), resultTopic.getTopic_id());
    }

    @Test
    @Order(3)
    public void verify_topic_service_returns_data() {
        List<Topic> topicList = topicService.findAll();
        Topic topic = new Topic();

        topic.setName("Bootstrap");

        assertEquals(topic.getName(), topicList.get(0).getName());
    }

    @Test
    @Order(4)
    public void verify_topic_service_can_find_one_topic() throws Exception {
        Topic topic = new Topic();
        topic.setName("Bootstrap");
        topic.setTopic_id(get_inserted_topic_id());

        Topic resultTopic = topicService.findById(topic.getTopic_id());

        assertEquals(topic.getName(), resultTopic.getName());
    }

    @Test
    @Order(5)
    public void verify_topic_service_can_delete_topics() throws Exception {
        List<Topic> topicList = topicService.findAll();
        int initial_length = topicList.size();

        topicService.delete(topicList.get(initial_length - 1).getTopic_id());

        int result_length = topicService.findAll().size();

        assertEquals(initial_length - 1, result_length);
    }
}