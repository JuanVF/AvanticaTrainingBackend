package com.avantica.proa.Controllers;

import com.avantica.proa.Models.User;
import com.avantica.proa.Models.UserRegistration;
import com.avantica.proa.Models.UserRegistrationReply;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserRegistrationController {

    @RequestMapping(method = RequestMethod.POST, value="/register/user")

    @ResponseBody
    public UserRegistrationReply registerUser(@RequestBody User user){
        UserRegistrationReply userRegReply = new UserRegistrationReply();
        UserRegistration.getInstance().add(user);

        userRegReply.setName(user.getName());
        userRegReply.setEmail(user.getEmail());
        userRegReply.setPassword(user.getPassword());

        return userRegReply;
    }
}
