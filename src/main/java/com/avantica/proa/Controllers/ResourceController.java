package com.avantica.proa.Controllers;

import com.avantica.proa.Models.Resource;
import com.avantica.proa.Models.Topic;
import com.avantica.proa.Repositories.ResourceRepository;
import com.avantica.proa.Services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ResourceRepository resourceRepository;

    @GetMapping("/resource")
    public ResponseEntity<List<Resource>> findAll() {
        return ResponseEntity.ok().body(resourceService.findAll());
    }

    @GetMapping("/resource/{id}")
    public ResponseEntity<Resource> findById(@PathVariable long id) throws Exception {
        return ResponseEntity.ok().body(resourceService.findById(id));
    }

    @PostMapping("/resource")
    public ResponseEntity<Resource> save(@Valid @RequestBody Resource resource) {
        return ResponseEntity.ok().body(resourceService.save(resource));
    }

    @PostMapping("/resource/relations/")
    public ResponseEntity<List<Resource>> findByTopicId(@Valid @RequestBody Topic topic) {
        return ResponseEntity.ok().body(resourceRepository.findAllByTopic(topic));
    }

    @PutMapping("/resource")
    public ResponseEntity<Resource> update(@Valid @RequestBody Resource resource) {
        return ResponseEntity.ok().body(resourceService.update(resource));
    }

    @DeleteMapping("/resource/{id}")
    public ResponseEntity<Resource> delete(@PathVariable long id) throws Exception {
        resourceService.delete(id);
        return ResponseEntity.ok().build();
    }
}
