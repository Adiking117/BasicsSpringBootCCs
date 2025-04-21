package com.adi.learning.tests.Entity;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Account {
    @Autowired
    User user;

    public Account(){
        System.out.println("Account Created");
    }

    @PostConstruct
    public void init(){
        System.out.println("Account created at "+this.hashCode()+ " and its user created at " + user
                .hashCode());
    }
}
