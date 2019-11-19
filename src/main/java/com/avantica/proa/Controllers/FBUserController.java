package com.avantica.proa.Controllers;

import com.avantica.proa.Models.FBUser;
import com.avantica.proa.Services.FBUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

            if (userRes != null) return ResponseEntity.ok().body(userRes);

            return ResponseEntity.status(406).build();
        } catch (Exception e) {
            return ResponseEntity.status(406).build();
        }
    }

    @PostMapping("/fb/login")
    public void loginWithFB(HttpServletRequest req,
                            HttpServletResponse res,
                            @RequestBody FBUser fbUser) throws IOException {
        fbUserService.loginWithFB(res, fbUser);
    }

    @Transactional
    @DeleteMapping("/fb/delete/{email}")
    public ResponseEntity delete(@PathVariable String email) throws Exception{
        fbUserService.deleteFBUser(email);

        return ResponseEntity.ok().build();
    }
}
