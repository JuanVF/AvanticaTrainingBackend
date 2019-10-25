package com.avantica.proa.Controllers;
import com.avantica.proa.Models.User;
import com.avantica.proa.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> findById(@PathVariable long id) throws Exception{
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @PostMapping("/user")
    public ResponseEntity<User> save(@RequestBody User user){
        return ResponseEntity.ok().body(userService.save(user));
    }

    @PutMapping("/user")
    public ResponseEntity<User> update(@RequestBody User user){
        return ResponseEntity.ok().body(userService.update(user));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> delete(@PathVariable long id) throws Exception{
        userService.delete(id);

        return ResponseEntity.ok().build();
    }
}
