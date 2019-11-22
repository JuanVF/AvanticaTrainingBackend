package com.avantica.proa.Controllers;

import com.avantica.proa.Models.Resource;
import com.avantica.proa.Models.Topic;
import com.avantica.proa.Services.TopicService;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ResourceControllerTest {
    @Autowired
    private ResourceController resourceController;

    @Autowired
    private TopicService topicService;

    public long get_last_topic_id() {
        List<Topic> result = topicService.findAll();

        return result.get(result.size() - 1).getTopic_id();
    }

    public long get_inserted_resource_id() {
        List<Resource> result = resourceController.findAll().getBody();

        return result.get(result.size() - 1).getResource_id();
    }

    @Test
    @Order(1)
    public void verify_resource_controller_can_save_users() {
        Resource resource = new Resource();
        Topic topic = new Topic();

        topic.setTopic_id(get_last_topic_id());

        resource.setTopic(topic);
        resource.setDescription("Node.js documentation");
        resource.setUrl("https://nodejs.org/es/");

        ResponseEntity<Resource> responseEntity = resourceController.save(resource);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Order(2)
    public void verify_resource_controller_can_find_by_id() throws Exception {
        ResponseEntity<Resource> responseEntity = resourceController.findById(get_inserted_resource_id());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @Order(3)
    public void verify_find_by_id_can_handle_no_existing_id() {

        Assertions.assertThrows(Exception.class, () -> {
            resourceController.findById(0);
        });
    }

    @Test
    @Order(4)
    public void verify_resource_controller_can_find_all_resources() {
        ResponseEntity<List<Resource>> responseEntity = resourceController.findAll();

        boolean isLengthHigherThanZero = responseEntity.getBody().size() > 0;

        assertTrue(isLengthHigherThanZero);
    }

    @Test
    @Order(5)
    public void verify_resource_controller_can_update_users() throws Exception {
        Resource updatedResource = new Resource();
        Topic topic = new Topic();

        topic.setTopic_id(get_last_topic_id());

        updatedResource.setUrl("https://platzi.com/clases/nodejs/");
        updatedResource.setDescription("Platzi node.js tutorial");
        updatedResource.setResource_id(get_inserted_resource_id());
        updatedResource.setTopic(topic);

        HttpStatus status = resourceController.update(updatedResource).getStatusCode();

        assertEquals(HttpStatus.OK, status);
    }

    @Test
    @Order(6)
    public void verify_resource_controller_can_delete_users() throws Exception {
        HttpStatus status = resourceController.delete(get_inserted_resource_id()).getStatusCode();

        assertEquals(HttpStatus.OK, status);
    }

    @Test
    @Order(7)
    public void verify_resource_controller_can_find_relations_that_does_not_exists() {
        Topic topicToFind = new Topic();
        topicToFind.setTopic_id(64564);

        List<Resource> resourcesList = resourceController.findByTopicId(topicToFind).getBody();
        int length = resourcesList.size();

        assertEquals(0, length);
    }
}