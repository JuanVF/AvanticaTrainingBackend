package com.avantica.proa.Controllers;

import com.avantica.proa.Models.Topic;
import com.avantica.proa.Services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping("/topic")
    public ResponseEntity<List<Topic>> findAll() {
        return ResponseEntity.ok().body(topicService.findAll());
    }

    @GetMapping("/topic/{id}")
    public ResponseEntity<Topic> findById(@PathVariable long id) throws Exception {
        return ResponseEntity.ok().body(topicService.findById(id));
    }

    @PostMapping("/topic")
    public ResponseEntity<Topic> save(@Valid @RequestBody Topic topic) {
        if(topic.getName() != null){
            return ResponseEntity.ok().body(topicService.save(topic));
        }

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PutMapping("/topic")
    public ResponseEntity<Topic> update(@Valid @RequestBody Topic topic) {
        if(topic.getName() != null && topic.getTopic_id() != 0){
            return ResponseEntity.ok().body(topicService.update(topic));
        }

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @DeleteMapping("/topic/{id}")
    public ResponseEntity<Topic> delete(@PathVariable long id) throws Exception {
        topicService.delete(id);
        return ResponseEntity.ok().build();
    }
}
