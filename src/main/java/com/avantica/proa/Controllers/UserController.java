package com.avantica.proa.Controllers;
import com.avantica.proa.Models.User;
import com.avantica.proa.Services.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    private final UserServiceImpl userServiceImpl;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserServiceImpl userServiceImpl,
                          BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userServiceImpl = userServiceImpl;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/users")
    public void save(@RequestBody User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }

    @GetMapping("/users/${email}")
    public User findByEmail(@PathVariable String email){
        User user = new User();
        user.setEmail(email);
        return userServiceImpl.findByEmail(user);
    }
}
