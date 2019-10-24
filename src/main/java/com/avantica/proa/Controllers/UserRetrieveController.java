package com.avantica.proa.Controllers;

import com.avantica.proa.Models.User;
import com.avantica.proa.Models.UserRegistration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserRetrieveController {
    @RequestMapping(method = RequestMethod.GET, value = "/user/allusers")

    @ResponseBody
    public List<User> getAllUsers(){
        return UserRegistration.getInstance().getUserRecords();
    }
}
