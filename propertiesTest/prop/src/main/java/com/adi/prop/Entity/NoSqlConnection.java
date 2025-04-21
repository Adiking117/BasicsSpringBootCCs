package com.adi.prop.Entity;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
// @ConditionalOnProperty(prefix = "nosqlconnection" , value = "enabled" , havingValue = "true" , matchIfMissing = false)
@Profile("dev")
public class NoSqlConnection {
    NoSqlConnection(){
        System.out.println("Initialization of NoSql Bean");
    }

    @Value("${username}")
    String username;

    @Value("${password}")
    String password;

    @PostConstruct
    public void init(){
        System.out.println("USername Nosql : "+username + " Password : "+ password);
    }
}