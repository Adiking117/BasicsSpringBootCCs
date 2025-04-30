package com.adi.aop.Controller;

import com.adi.aop.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class EmployeeController {

    @GetMapping("/employee")
    public String fetchEmployee(){
        return "Employee fetched";
    }

    @GetMapping("/employee/id/{id}")
    public String fetchEmployeeWithId(@PathVariable int id){
        return "Employee with "+id + " fetched";
    }

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee/name/{name}")
    public String fetchEmployee(@PathVariable String name){
        return employeeService.EmployeeHelper(name);
    }

    @GetMapping("/employee/details")
    public void getEmployeeDetails(){
        employeeService.getEmployeeDetails();
    }






}
