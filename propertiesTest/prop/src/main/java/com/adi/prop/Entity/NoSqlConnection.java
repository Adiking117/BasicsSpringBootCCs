package com.adi.prop.Entity;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "nosqlconnection" , value = "enabled" , havingValue = "true" , matchIfMissing = false)
public class NoSqlConnection {
    NoSqlConnection(){
        System.out.println("Initialization of NoSql Bean");
    }
}