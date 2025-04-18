package com.adispringboot.springbootlearning.Entity;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
// @Lazy
// @Scope("prototype") // // uncomment this for getting new instnace each time
public class OrderEntity {
    public OrderEntity() {
        System.out.println("Order Entity Constructor "+this.hashCode());
    }

    public String getOrder() {
        return "Order MEthod called";
    }

}
