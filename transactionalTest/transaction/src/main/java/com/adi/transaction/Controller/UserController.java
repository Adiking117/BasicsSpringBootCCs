package com.adi.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction/")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public String getTransaction(){
        return userService.getUserDetails();
    }

    @GetMapping("/update/{rollback}")
    public void updateUser(@PathVariable boolean rollback){
        userService.updateUserProgrammatic(rollback);
    }


}
