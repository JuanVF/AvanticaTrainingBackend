package com.avantica.proa.Controllers;

import com.avantica.proa.Models.User;
import com.avantica.proa.Services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Controller
public class UserController {
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<User> save(@RequestBody User user) {
        User verifier = userDetailsServiceImpl.findByEmail(user.getEmail());

        if (verifier == null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            return ResponseEntity.ok().body(userDetailsServiceImpl.save(user));
        }
        return ResponseEntity.status(406).build();
    }

    @Transactional
    @DeleteMapping("/user/delete/{email}")
    public ResponseEntity delete(@PathVariable String email) throws Exception{
        userDetailsServiceImpl.deleteByEmail(email);

        return ResponseEntity.ok().build();
    }
}
