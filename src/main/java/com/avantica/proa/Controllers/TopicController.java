package com.avantica.proa.Controllers;

import com.avantica.proa.Models.Topic;
import com.avantica.proa.Services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping("/topic")
    public ResponseEntity<List<Topic>> findAll(){
        return ResponseEntity.ok().body(topicService.findAll());
    }

    @GetMapping("/topic/{id}")
    public ResponseEntity<Topic> findById(@PathVariable long id) throws Exception{
        return ResponseEntity.ok().body(topicService.findById(id));
    }

    @PostMapping("/topic")
    public ResponseEntity<Topic> save(@RequestBody Topic topic){
        return ResponseEntity.ok().body(topicService.save(topic));
    }

    @PutMapping("/topic")
    public ResponseEntity<Topic> update(@RequestBody Topic topic){
        return ResponseEntity.ok().body(topicService.update(topic));
    }

    @DeleteMapping("/topic/{id}")
    public ResponseEntity<Topic> delete(@PathVariable long id) throws Exception{
        topicService.delete(id);
        return ResponseEntity.ok().build();
    }
}
