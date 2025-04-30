package com.adi.transaction.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component
public class PropServiceA {
    @Autowired
    private PropServiceB serviceB;

    @Transactional
    public void outerMethod() {

        System.out.println("Is Transaction A Active "+ TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("A Transaction Name "+ TransactionSynchronizationManager.getCurrentTransactionName());
        serviceB.innerMethod();

    }
}
