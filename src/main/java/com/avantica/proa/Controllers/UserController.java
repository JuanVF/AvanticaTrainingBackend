package com.avantica.proa.Controllers;
import com.avantica.proa.FBTokenUtils;
import com.avantica.proa.Models.User;
import com.avantica.proa.Services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.avantica.proa.Security.SecurityConstants.FB_USER_TOKEN;

@RestController
@CrossOrigin
@Controller
public class UserController {
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/signup")
    ResponseEntity<User> save(@RequestBody User user){
        User verifier = userDetailsServiceImpl.findByEmail(user.getEmail());

        if(verifier == null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            return ResponseEntity.ok().body(userDetailsServiceImpl.save(user));
        }
        return ResponseEntity.status(406).build();
    }

    @PostMapping("/fb/signup")
    ResponseEntity saveFBUser(@RequestBody User user) throws Exception {
        User verifier = userDetailsServiceImpl.findByEmail(user.getEmail());

        if(verifier == null){
            String generatedPass = FB_USER_TOKEN;
            user.setPassword(bCryptPasswordEncoder.encode(generatedPass));

            try {
                User userRes = userDetailsServiceImpl.saveFBUser(user);
                userRes.setPassword("");

                return ResponseEntity.ok().body(userRes);
            }catch (Exception e){
                return ResponseEntity.status(406).build();
            }

        }
        return ResponseEntity.status(406).build();
    }
}
