package com.avantica.proa.Controllers;

import com.avantica.proa.Models.Topic;
import com.avantica.proa.Services.TopicServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TopicController {
    private final TopicServiceImpl topicServiceImpl;

    public TopicController(TopicServiceImpl topicServiceImpl) {
        this.topicServiceImpl = topicServiceImpl;
    }

    @GetMapping("/topic")
    public ResponseEntity<List<Topic>> findAll(){
        return ResponseEntity.ok().body(topicServiceImpl.findAll());
    }

    @GetMapping("/topic/{id}")
    public ResponseEntity<Topic> findById(@PathVariable long id) throws Exception{
        return ResponseEntity.ok().body(topicServiceImpl.findById(id));
    }

    @PostMapping("/topic")
    public ResponseEntity<Topic> save(@RequestBody Topic topic){
        return ResponseEntity.ok().body(topicServiceImpl.save(topic));
    }

    @PutMapping("/topic")
    public ResponseEntity<Topic> update(@RequestBody Topic topic){
        return ResponseEntity.ok().body(topicServiceImpl.update(topic));
    }

    @DeleteMapping("/topic/{id}")
    public ResponseEntity<Topic> delete(@PathVariable long id) throws Exception{
        topicServiceImpl.delete(id);
        return ResponseEntity.ok().build();
    }
}
