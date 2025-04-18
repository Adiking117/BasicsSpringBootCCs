package com.adi.learning.tests.Controller;

import com.adi.learning.tests.Entity.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tests/1/")
//@Scope(value= ConfigurableBeanFactory.SCOPE_SINGLETON)
// // Only one instance created per IOC
@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
// // Each time new instance created by IOC when called this
public class TestController2 {
    private User user;

    public TestController2(User user){
        this.user = user;
        System.out.println("Test Controller 2 Created");
    }

    @PostConstruct
    public void init(){
        System.out.println("Test Controller 2 created at "+this.hashCode()+ " and its user created at " + user
                .hashCode());
    }

    @GetMapping("/user/2")
    public String getUser(){
        return "user details 2 fetched";
    }
}
