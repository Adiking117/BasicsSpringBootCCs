package com.adi.transaction.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class UserDAO {

    private JdbcTemplate jdbcTemplate;
    PlatformTransactionManager userTransactionManager;
    TransactionTemplate transactionTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate, PlatformTransactionManager userTransactionManager, TransactionTemplate transactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.userTransactionManager = userTransactionManager;
        this.transactionTemplate = transactionTemplate;
    }

    // Direct JDBC Operation (Declarative)
    public void updateUserDeclarative(String name, String email) {
        jdbcTemplate.update("INSERT INTO users(name, email) VALUES (?, ?)", name, email);
    }

    // Programmatic Approach 1
    public void updateUserProgrammatic1(){
        TransactionStatus status = userTransactionManager.getTransaction(null);
        try{
            // DB opeartions
            System.out.println("Update Query Run");
            // DB opeartions
            userTransactionManager.commit(status);
        } catch (Exception e){
            userTransactionManager.rollback(status);
        }
    }


    // Programmatic Approach 2
    public void updateUserProgrammatic2(boolean rollback){
        TransactionCallback<TransactionStatus> dbOperations = (TransactionStatus status) -> {
            System.out.println("▶ Programmatic Txn Started");

            jdbcTemplate.update("INSERT INTO users(name, email) VALUES (?, ?)", "Alice", "alice@example.com");

            System.out.println("➡ External API call simulated");
            // simulateExternalApiCall();

            if (rollback) {
                throw new RuntimeException("❌ Simulated Exception in Programmatic Txn");
            }

            jdbcTemplate.update("UPDATE users SET name = ? WHERE email = ?", "AliceUpdated", "alice@example.com");

            System.out.println("✔ Programmatic Transaction Completed");

            System.out.println("Propagation Required : Is Transaction Active "+ TransactionSynchronizationManager.isActualTransactionActive());
            System.out.println("Propagation Required : Current Transaction Name "+ TransactionSynchronizationManager.getCurrentTransactionName());

            return status;
        };
        TransactionStatus status = transactionTemplate.execute(dbOperations);
    }
}
