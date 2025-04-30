package com.adi.JDBC.Controller;

import com.adi.JDBC.DAO.PlainJDBCUserDAO;
import com.adi.JDBC.POJO.User;
import com.adi.JDBC.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jdbc")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/create/table")
    public String createUserTable(){
        userService.createTable();
        return "Table created Successfully";
    }

    @GetMapping("/insert/users/{name}/{age}")
    public String insertUser(@PathVariable String name,@PathVariable int age){
        userService.insertUser(name,age);
        return "User created Successfully";
    }


    @GetMapping("/get/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.readUsers());
    }

    @GetMapping("/get/usernames")
    public ResponseEntity<List<String>> getAllUserNames() {
        return ResponseEntity.ok(userService.getAllUserNames());
    }

    @GetMapping("/get/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/get/users/count")
    public int getUserById() {
        return userService.getCountOfUsers();
    }

}
