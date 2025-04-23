package com.adi.transaction.Service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component
public class PropServiceB {
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void innerMethod() {
        System.out.println("Is Transaction B Active "+ TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("B Transaction Name "+ TransactionSynchronizationManager.getCurrentTransactionName());
        // throw new RuntimeException("Error in innerMethod"); // Uncomment to test rollback behavior
    }
}
