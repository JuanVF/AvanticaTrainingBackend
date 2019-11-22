package com.avantica.proa.Services;

import com.avantica.proa.Models.Resource;
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
class ResourceServiceTest {
    @Autowired
    private ResourceService resourceService;

    @Autowired
    private TopicService topicService;

    public long get_last_topic_id(){
        List<Topic> result = topicService.findAll();

        return result.get(result.size() - 1).getTopic_id();
    }

    public long get_inserted_resource_id(){
        List<Resource> result = resourceService.findAll();

        return result.get(result.size() - 1).getResource_id();
    }

    @Test
    @Order(1)
    public void verify_resource_service_can_save_resources() {
        Resource resource = new Resource();
        Topic topic = new Topic();

        topic.setTopic_id(get_last_topic_id());

        resource.setDescription("Tables in bootstrap");
        resource.setUrl("https://getbootstrap.com/docs/4.3/components/alerts/");
        resource.setTopic(topic);

        Resource resultResource = resourceService.save(resource);

        assertEquals("Tables in bootstrap", resultResource.getDescription());
    }

    @Test
    @Order(2)
    public void verify_resource_service_can_find_resources() {
        List<Resource> resources = resourceService.findAll();

        assertEquals("Tables in bootstrap", resources.get(resources.size() - 1).getDescription());
    }

    @Test
    @Order(3)
    public void verify_resource_service_can_update_resources() {
        Resource resource = new Resource();
        Topic topic = new Topic();
        topic.setTopic_id(get_last_topic_id());

        resource.setUrl("https://getbootstrap.com/docs/4.0/components/alerts/");
        resource.setDescription("How to create Alerts in Bootstrap");
        resource.setResource_id(get_inserted_resource_id());
        resource.setTopic(topic);

        Resource resultResource = resourceService.update(resource);

        assertEquals("How to create Alerts in Bootstrap", resultResource.getDescription());
    }

    @Test
    @Order(4)
    public void verify_resource_service_can_find_by_id() throws Exception {
        Resource foundResource = resourceService.findById(get_inserted_resource_id());

        assertEquals("How to create Alerts in Bootstrap", foundResource.getDescription());
    }

    @Test
    @Order(5)
    public void verify_resource_service_can_delete_resources() throws Exception {
        List<Resource> resourceList = resourceService.findAll();
        int initial_length = resourceList.size();

        resourceService.delete(resourceList.get(initial_length - 1).getResource_id());

        int result_length = resourceService.findAll().size();

        assertEquals(initial_length - 1, result_length);
    }
}