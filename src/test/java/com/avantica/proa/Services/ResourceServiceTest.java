package com.avantica.proa.Services;

import static org.junit.Assert.*;

import com.avantica.proa.Models.Resource;
import com.avantica.proa.Models.Topic;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class ResourceServiceTest {
    @Autowired
    private ResourceService resourceService;

    @Test
    public void verify_resource_service_can_save_resources(){
        Resource resource = new Resource();
        Topic topic = new Topic();

        topic.setTopic_id(1);

        resource.setDescription("Tables in bootstrap");
        resource.setUrl("https://getbootstrap.com/docs/4.3/components/alerts/");
        resource.setTopic(topic);

        Resource resultResource = resourceService.save(resource);

        assertEquals("Tables in bootstrap",resultResource.getDescription());
    }

    @Test
    public void verify_resource_service_can_find_resources(){
        List<Resource> resources = resourceService.findAll();

        assertEquals("Tables in bootstrap",resources.get(0).getDescription());
    }

    @Test
    public void verify_resource_service_can_update_resources(){
        Resource resource = new Resource();
        Topic topic = new Topic();
        topic.setTopic_id(1);

        resource.setUrl("https://getbootstrap.com/docs/4.0/components/alerts/");
        resource.setDescription("How to create Alerts in Bootstrap");
        resource.setResource_id(2);
        resource.setTopic(topic);

        Resource resultResource = resourceService.update(resource);

        assertEquals("How to create Alerts in Bootstrap", resultResource.getDescription());
    }
    
    @Test
    public void verify_resource_service_can_delete_resources() throws Exception {
        List<Resource> resourceList = resourceService.findAll();
        int initial_length = resourceList.size();

        resourceService.delete(resourceList.get(initial_length-1).getResource_id());

        int result_length = resourceService.findAll().size();

        assertEquals(initial_length-1,result_length);
    }

    @Test
    public void verify_resource_service_can_find_by_id() throws Exception{
        Resource foundResource = resourceService.findById(1);

        assertEquals("Tables in bootstrap",foundResource.getDescription());
    }
}