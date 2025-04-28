package com.adi.JDBC.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public Connection getConnection(){
        try{
            // H2 Driver loading
            Class.forName("org.h2.Driver");

            // Extablish Connection with DB
            return DriverManager.getConnection("jdbc:h2:mem:userDB","sa","");


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // return null;
    }
}
