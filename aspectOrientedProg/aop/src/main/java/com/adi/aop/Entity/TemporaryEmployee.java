package com.adi.aop.Entity;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("temporaryEmployee")
public class TemporaryEmployee implements IEmployee{
    @Override
    public void fetchEmployeeMethod() {
        System.out.println("Temporary Employee fetch Method");
    }
}
