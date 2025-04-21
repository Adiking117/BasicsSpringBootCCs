package com.adi.learning.tests.Controller;

import com.adi.learning.tests.Entity.Account;
import com.adi.learning.tests.Entity.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tests/1/")
// @Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE) // Every time called new instance created
// @Scope("request") // Every HTTP request
// @Lazy // One time new instance
public class BeanScopesTestController {

    //@Autowired
    private User user;
    private Account account;

    public BeanScopesTestController(User user, Account account){
        System.out.println("Test Controller 1 Creation Started");
        this.user = user;
        this.account = account;
        System.out.println("Test Controller 1 Created");
    }

    @PostConstruct
    public void init(){
        System.out.println("Test Controller 1 created at "+this.hashCode()+
                " and its user created at " + user.hashCode()+
                " and its account created at " + account.hashCode()
        );
    }

    @GetMapping("/user/1")
    public String getUser(){
        return "user details 1 fetched";
    }

}
