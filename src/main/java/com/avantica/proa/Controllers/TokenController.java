package com.avantica.proa.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TokenController {
    @PostMapping("/token/verify")
    public ResponseEntity verifyToken(){
        return ResponseEntity.ok().build();
    }
}
