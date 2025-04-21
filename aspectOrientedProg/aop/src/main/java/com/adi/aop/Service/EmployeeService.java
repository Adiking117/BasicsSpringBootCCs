package com.adi.aop.Service;

import com.adi.aop.Entity.IEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    @Qualifier("permanentEmployee")
    IEmployee employee;

    public String EmployeeHelper(String name) {
        return "Employee with name " + name + " fetched";
    }

    public void getEmployeeDetails(){
        employee.fetchEmployeeMethod();
    }
}
