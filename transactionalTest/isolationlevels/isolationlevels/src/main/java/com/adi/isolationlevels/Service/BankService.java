package com.adi.isolationlevels.Service;

import com.adi.isolationlevels.DAO.BankDAO;
import com.adi.isolationlevels.Entity.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankService {

    @Autowired
    private BankDAO bankAccountRepository;

    // Method 1: Read the balance
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public double readBalance(Long id) {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow();
        System.out.println("Reading balance: " + account.getBalance());
        return account.getBalance();
    }

    // Method 2: Update the balance (simulate long transaction)
    @Transactional
    public void updateBalance(Long id, double newBalance) {
        BankAccount account = bankAccountRepository.findById(id).orElseThrow();
        account.setBalance(newBalance);
        bankAccountRepository.save(account);
        try {
            Thread.sleep(5000); // Simulate delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

