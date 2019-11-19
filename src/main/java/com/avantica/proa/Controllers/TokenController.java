package com.avantica.proa.Controllers;

import com.avantica.proa.Security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class TokenController {
    @PostMapping("/token/verify")
    public ResponseEntity verifyToken(HttpServletRequest httpServletRequest) {
        Authentication auth = JwtUtil.getAuthentication(httpServletRequest);

        if (auth == null) return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

        return ResponseEntity.ok().build();
    }
}
