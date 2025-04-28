package com.adi.JDBC.Service;

import com.adi.JDBC.DAO.UserDAO;
import com.adi.JDBC.POJO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    @Qualifier("JDBCTemplate")
    UserDAO userDAO;

    public void createTable(){
        userDAO.createUserTable();
    }

    public void insertUser(String userName,int userAge){
        userDAO.createUser(userName,userAge);
    }

    public List<User> readUsers(){
        return userDAO.readUsers();
    }

    public List<String> getAllUserNames(){
        return userDAO.readUserNames();
    }

    public User getUserById(int userId){
        return userDAO.readUserById(userId);
    }

    public int getCountOfUsers(){
        return userDAO.getCountOfUsers();
    }
}
