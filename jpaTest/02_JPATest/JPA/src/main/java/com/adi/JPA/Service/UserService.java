package com.adi.JPA.Service;

import com.adi.JPA.DAO.UserDAO;
import com.adi.JPA.Entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public User createUser(User user){
        return userDAO.save(user);
    }

    public List<User> getAllUsers(){
        return userDAO.findAll();
    }

    //@Autowired
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void saveUser(User user){
        entityManager.persist(user);
    }
}
