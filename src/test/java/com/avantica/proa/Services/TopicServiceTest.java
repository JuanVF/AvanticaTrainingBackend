package com.avantica.proa.Services;

import com.avantica.proa.Models.Topic;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TopicServiceTest {
    @Autowired
    private TopicService topicService;

    @Test
    public void verify_topic_service_returns_data(){
        List<Topic> topicList = topicService.findAll();
        Topic topic = new Topic();

        topic.setTopic_id(1);
        topic.setName("Bootstrap");

        assertEquals(topic.getName(),topicList.get(0).getName());
    }

    @Test
    public void verify_topic_service_can_save_topics(){
        Topic topic = new Topic();

        topic.setName("Node.js");

        Topic resultTopic = topicService.save(topic);

        assertEquals("Node.js",resultTopic.getName());
    }

    @Test
    public void verify_topic_service_can_update_topics(){
        Topic topic = new Topic();

        topic.setName("Bootstrap");
        topic.setTopic_id(1);

        Topic resultTopic = topicService.update(topic);

        assertEquals(1, resultTopic.getTopic_id());
    }

    @Test
    public void verify_topic_service_can_find_one_topic() throws Exception {
        Topic topic = new Topic();

        topic.setTopic_id(1);

        Topic resultTopic = topicService.findById(topic.getTopic_id());

        assertEquals("Bootstrap",resultTopic.getName());
    }

    @Test
    public void verify_topic_service_can_delete_topics() throws Exception{
        List<Topic> topicList = topicService.findAll();
        int initial_length = topicList.size();

        topicService.delete(topicList.get(initial_length-1).getTopic_id());

        int result_length = topicService.findAll().size();

        assertEquals(initial_length-1,result_length);
    }
}