package com.adi.Async.DAO;

import com.adi.Async.Exceptions.InsufficeintBalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class UserDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Async
    public void updateUserBalance1(int id,double balance){
        jdbcTemplate.update("UPDATE users set balance = ? where id = ? ",id,balance);
    }

    public void updateUserBalance2(int id,double balance){
        jdbcTemplate.update("UPDATE users set balance = ? where id = ? ",id,balance);
    }

    @Transactional
    public void updateUserBalance3(int id,double balance){
        jdbcTemplate.update("UPDATE users set balance = ? where id = ? ",id,balance);
    }


    @Transactional
    public void debitBalance(int id, double balanceToDebit) throws InsufficeintBalanceException{
        BigDecimal ogBalance = jdbcTemplate.queryForObject(
                "SELECT balance FROM users WHERE id = ?",
                BigDecimal.class,
                id
        );
        BigDecimal newBalance = ogBalance.subtract(BigDecimal.valueOf(balanceToDebit));
        if((newBalance.compareTo(BigDecimal.ZERO) < 0)){
            throw new InsufficeintBalanceException("Balance ille no layki");
        }
        jdbcTemplate.update(
                "UPDATE users SET balance = ? WHERE id = ?",
                newBalance,
                id
        );
    }

}
