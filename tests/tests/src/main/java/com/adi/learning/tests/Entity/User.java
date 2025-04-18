package com.adi.learning.tests.Entity;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
// @Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS)
// // Spring creates a proxy that resolves to the correct request-scoped User during the actual request.
@Scope("prototype")
public class User {
    public User(){
        System.out.println("User Created");
    }

    @PostConstruct
    public void init(){
        System.out.println("User created at " + this
                .hashCode());
    }
}
