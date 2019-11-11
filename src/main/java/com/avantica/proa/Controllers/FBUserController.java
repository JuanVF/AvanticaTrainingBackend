package com.avantica.proa.Controllers;

import com.avantica.proa.Models.FBUser;
import com.avantica.proa.Services.FBUserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@CrossOrigin
@Controller
public class FBUserController {
    @Autowired
    private FBUserService fbUserService;

    @PostMapping("/fb/signup")
    ResponseEntity saveFBUser(@RequestBody FBUser user) throws Exception {
        try {
            FBUser userRes = fbUserService.saveFBUser(user);

            if(userRes != null) return ResponseEntity.ok().body(userRes);

            return ResponseEntity.status(406).build();
        }catch (Exception e){
            return ResponseEntity.status(406).build();
        }
    }

    @PostMapping("/fb/login")
    public void loginWithFB(HttpServletRequest req,
                            HttpServletResponse res,
                            @RequestBody FBUser fbUser) throws IOException {
        fbUserService.loginWithFB(res,fbUser);
    }
}
