package com.adi.aop.Entity;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("permanentEmployee")
public class PermanentEmployee implements IEmployee{

    @Override
    public void fetchEmployeeMethod() {
        System.out.println("Permanent Employee fetch Method");
    }

}
