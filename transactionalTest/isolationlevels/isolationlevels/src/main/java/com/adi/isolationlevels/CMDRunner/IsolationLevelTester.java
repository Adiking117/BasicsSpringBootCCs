package com.adi.isolationlevels.CMDRunner;

import com.adi.isolationlevels.DAO.BankDAO;
import com.adi.isolationlevels.Entity.BankAccount;
import com.adi.isolationlevels.Service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class IsolationLevelTester implements CommandLineRunner {

    @Autowired
    private BankService bankService;

    @Autowired
    private BankDAO bankAccountRepository;

    @Override
    public void run(String... args) throws Exception {
        // Clean slate setup
        BankAccount account = new BankAccount("John Doe",1000.0,1L);

        bankAccountRepository.save(account);

        // Thread 1: Read balance
        Thread readThread = new Thread(() -> {
            try {
                Thread.sleep(1000); // wait a bit so update starts first
                double balance = bankService.readBalance(1L);
                System.out.println("Thread 1 - Read Balance: " + balance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Thread 2: Update balance
        Thread updateThread = new Thread(() -> {
            try {
                bankService.updateBalance(1L, 2000.0);
                System.out.println("Thread 2 - Updated Balance to 2000");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Start both threads
        updateThread.start();
        readThread.start();

        // Wait for both to complete
        updateThread.join();
        readThread.join();

        // Final check
        System.out.println("Final DB Balance: " + bankAccountRepository.findById(1L).get().getBalance());
    }
}
