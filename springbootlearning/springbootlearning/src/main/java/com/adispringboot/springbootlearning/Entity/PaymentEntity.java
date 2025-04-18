package com.adispringboot.springbootlearning.Entity;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class PaymentEntity {
    private Long Id;
    private double paymentAmount;
    private String paymentCurrency;
    private String userEmail;

     // 2 nd to called
    @Autowired
    private OrderEntity orderEntity;

    @Autowired
    @Lazy
    private OrderEntity orderEntity1;

//    // 3 rd to called
//    @PostConstruct
//    public void initialize(){
//        System.out.println("PAyment Post construct");
//    }
//
//    // 4 th to called
//    @PreDestroy
//    public void destroyBean(){
//        System.out.println("PAyment Pre destroy");
//    }

    // 1 st to called
    public PaymentEntity(){
        System.out.println("PAyment Entity default constructor"+" " +this.hashCode());
        // orderEntity.getOrder(); // Dependancy not yet injected
    }

    // Called via AppConfig Beans
    public PaymentEntity(Long id,String userEmail){
        System.out.println("PAyment Entity parameterised contrustur "+id+" "+userEmail + " " +this.hashCode());
    }
     // // Error while starting application , therefore Bean comes into picture

    public void accessOrder() {
        System.out.println("Accessing Order bean");
        System.out.println(orderEntity1.getOrder());
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getPaymentCurrency() {
        return paymentCurrency;
    }

    public void setPaymentCurrency(String paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }



}
