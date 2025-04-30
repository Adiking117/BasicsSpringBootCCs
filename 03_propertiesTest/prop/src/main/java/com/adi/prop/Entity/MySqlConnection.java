package com.adi.prop.Entity;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
// @ConditionalOnProperty(prefix = "sqlconnection" , value = "enabled" , havingValue = "true" , matchIfMissing = false)
@Profile("qa")
public class MySqlConnection {

    MySqlConnection(){
        System.out.println("Initialization of MySqlBean");
    }

    @Value("${username}")
    String username;

    @Value("${password}")
    String password;

    @PostConstruct
    public void init(){
        System.out.println("USurname Mysql : "+username + " Password : "+ password);
    }

}
