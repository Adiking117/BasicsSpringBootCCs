package com.adi.prop.Entity;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DBConnection {
    @Autowired(required=false)
    MySqlConnection mySqlConnection;

    @Autowired(required=false)
    NoSqlConnection noSqlConnection;
    /* DBConnection prevents Spring from throwing an error
    if the dependencies are unavailable, but it means that null values can be injected instead.
    If sqlconnection.enabled = false, mySqlConnection would be null, and the same goes for noSqlConnection*/
    @PostConstruct
    public void init(){
        System.out.println("MySql " + Objects.isNull(mySqlConnection));
        System.out.println("NoSql " + Objects.isNull(noSqlConnection));
    }
}
