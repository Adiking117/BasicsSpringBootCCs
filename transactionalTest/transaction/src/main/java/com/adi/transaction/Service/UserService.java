package com.adi.transaction.Service;

import com.adi.transaction.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
public class UserService {

    @Transactional
    public String getUserDetails(){
        return "User fetched Success";
    }

    // Declarative Approach
    @Transactional(transactionManager = "userTransactionManager")
    public void updateUserDeclarative(){
        // some DB Operations
        System.out.println("Insert Query Run");
    }

    // Suppose we need to call APIs between DB operations
//    @Transactional
//    public void updateUserProgrammatic(){
//        // DB insert
//        // External API Call
//        // DB update
//    }



    @Autowired
    UserDAO userDAO;

    @Transactional
    public void updateUserProgrammatic(boolean rollback){
        System.out.println("Is Transaction Active "+ TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("Current Transaction Name "+ TransactionSynchronizationManager.getCurrentTransactionName());

        System.out.println("Initial DB Operation");
        // userDAO.updateUserProgrammatic1();
        userDAO.updateUserProgrammatic2(rollback);
        System.out.println("Final DB Operation");
    }

    public void updateUserProgrammaticNonTransactional(boolean rollback){
        System.out.println("Is Transaction Active "+ TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("Current Transaction Name "+ TransactionSynchronizationManager.getCurrentTransactionName());

        System.out.println("Initial DB Operation");
        // userDAO.updateUserProgrammatic1();
        userDAO.updateUserProgrammatic2(rollback);
        System.out.println("Final DB Operation");
    }


}