package com.adi.transaction.Controller;

import com.adi.transaction.Service.PropServiceA;
import com.adi.transaction.Service.UserService;
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

    @Autowired
    private PropServiceA serviceA;

    @GetMapping("/propTest")
    public String testTransaction() {
        serviceA.outerMethod();
        return "Done";
    }




}
