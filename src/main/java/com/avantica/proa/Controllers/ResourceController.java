package com.avantica.proa.Controllers;

import com.avantica.proa.Models.Resource;
import com.avantica.proa.Services.IResourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ResourceController {
    private final IResourceService resourceService;

    public ResourceController(IResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping("/resource")
    public ResponseEntity<List<Resource>> findAll(){
        return ResponseEntity.ok().body(resourceService.findAll());
    }

    @GetMapping("/resource/{id}")
    public ResponseEntity<Resource> findById(@PathVariable long id) throws Exception{
        return ResponseEntity.ok().body(resourceService.findById(id));
    }

    @PostMapping("/resource")
    public ResponseEntity<Resource> save(@RequestBody Resource resource){
        return ResponseEntity.ok().body(resourceService.save(resource));
    }

    @PutMapping("/resource")
    public ResponseEntity<Resource> update(@RequestBody Resource resource){
        return ResponseEntity.ok().body(resourceService.update(resource));
    }

    @DeleteMapping("/resource/{id}")
    public ResponseEntity<Resource> delete(@PathVariable long id) throws Exception{
        resourceService.delete(id);
        return ResponseEntity.ok().build();
    }
}
