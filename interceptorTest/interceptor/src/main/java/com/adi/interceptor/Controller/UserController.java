package com.adi.interceptor.Controller;

import com.adi.interceptor.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/interceptor")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getUser")
    public String getUser(){
        userService.getUser();
        return "Success";
    }

    @GetMapping("/update/customI")
    public String updateUser(){
        userService.updateUser();
        return "Custom Interceptor";
    }

}
