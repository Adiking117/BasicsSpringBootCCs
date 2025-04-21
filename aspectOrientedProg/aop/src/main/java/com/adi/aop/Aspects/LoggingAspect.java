package com.adi.aop.Aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

//    @Before("execution(public String com.adi.aop.Controller.Employee.fetchEmployee())")
//    public void beforeMethod(){
//        System.out.println("Inside Before Method Aspect");
//    }

    // Matches any return type
    @Before("execution(* com.adi.aop.Controller.Employee.fetchEmployee())")
    public void beforeEmployeeFetch(){
        System.out.println("Before Employee fetch Method Aspect");
    }

    // Matches any method with single parameter String
    @Before("execution(* com.adi.aop.Controller.Employee.*(int))")
    public void beforeEmployeeFetchWithId(){
        System.out.println("Before Employee with ID fetch Method Aspect");
    }

    // Run for each method in Employee Class
    @Before("within(com.adi.aop.Controller.Employee)")
    public void beforeEveryMethod(){
        System.out.println("Before Every Employee Class fetch Method Aspect");
    }

    // Matches any method in a class which has annotation
    @Before("@within(org.springframework.stereotype.Service)")
    public void beforeServiceMethod(){
        System.out.println("Before Every Service Method Aspect ");
    }

    // Matches any method that is annotated with given annotation
    @Before("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void beforeGetMapping(){
        System.out.println("Before Every Get Mapping Method Aspect ");
    }

    @Before("target(com.adi.aop.Entity.IEmployee)")
    public void beforeInterfaceImplementedMethod(){
        System.out.println("Before Interface Implemented MMethod Aspect");
    }

    // Around
    @Around("execution(* com.adi.aop.Service.EmployeeService.*(..))")
    public void aroundServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("Before EmployeeService");
        joinPoint.proceed();
        System.out.println("After EmployeeService");
    }







}
