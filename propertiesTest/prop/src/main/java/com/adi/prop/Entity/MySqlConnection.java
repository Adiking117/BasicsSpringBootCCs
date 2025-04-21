package com.adi.prop.Entity;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "sqlconnection" , value = "enabled" , havingValue = "true" , matchIfMissing = false)
public class MySqlConnection {
    MySqlConnection(){
        System.out.println("Initialization of MySqlBean");
    }
}
