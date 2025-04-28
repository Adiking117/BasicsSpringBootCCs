package com.adi.JDBC.DAO;

import com.adi.JDBC.POJO.User;

import java.util.List;

public interface UserDAO {
    void createUserTable();
    void createUser(String userName,int userAge);
    List<User> readUsers();

    List<String> readUserNames();

    User readUserById(int userId);

    int getCountOfUsers();
}
