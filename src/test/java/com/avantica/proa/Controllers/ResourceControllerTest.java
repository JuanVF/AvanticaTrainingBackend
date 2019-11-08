package com.avantica.proa.Controllers;

import static org.junit.Assert.*;

import com.avantica.proa.Models.Resource;
import com.avantica.proa.Models.Topic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class ResourceControllerTest {
    @Autowired
    private ResourceController resourceController;

    @Test
    public void verify_resource_controller_can_find_by_id() throws Exception {
        ResponseEntity<Resource> responseEntity = resourceController.findById(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void verify_find_by_id_can_handle_no_existing_id(){

        Assertions.assertThrows(Exception.class, ()->{
           resourceController.findById(45445456);
        });
    }

    @Test
    public void verify_resource_controller_can_find_all_resources(){
        ResponseEntity<List<Resource>> responseEntity = resourceController.findAll();

        boolean isLengthHigherThanZero = responseEntity.getBody().size() > 0;

        assertTrue(isLengthHigherThanZero);
    }

    @Test
    public void verify_resource_controller_can_save_users(){
        Resource resource = new Resource();
        Topic topic = new Topic();

        topic.setTopic_id(2);

        resource.setTopic(topic);
        resource.setDescription("Node.js documentation");
        resource.setUrl("https://nodejs.org/es/");

        ResponseEntity<Resource> responseEntity = resourceController.save(resource);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void verify_resource_controller_can_update_users() throws Exception {
        Resource updatedResource = new Resource();
        Topic topic = new Topic();

        topic.setTopic_id(2);

        updatedResource.setUrl("https://platzi.com/clases/nodejs/");
        updatedResource.setDescription("Platzi node.js tutorial");
        updatedResource.setResource_id(3);
        updatedResource.setTopic(topic);

        HttpStatus status = resourceController.update(updatedResource).getStatusCode();

        assertEquals(HttpStatus.OK, status);
    }

    @Test
    public void verify_resource_controller_can_delete_users() throws Exception{
        List<Resource> resourceList = resourceController.findAll().getBody();
        int initial_length = resourceList.size();

        long lastId = resourceList.get(initial_length-1).getResource_id();

        HttpStatus status = resourceController.delete(lastId).getStatusCode();

        assertEquals(HttpStatus.OK,status);
    }

    @Test
    public void verify_resource_controller_can_find_relations_that_does_not_exists(){
        Topic topicToFind = new Topic();
        topicToFind.setTopic_id(45454545);

        List<Resource> resourcesList = resourceController.findByTopicId(topicToFind).getBody();
        int length = resourcesList.size();

        assertEquals(0, length);
    }
}