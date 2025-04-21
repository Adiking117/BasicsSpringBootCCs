package com.adispringboot.springbootlearning.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// @Qualifier("onlineOrder")
// @Component
// @Lazy
// @Scope("prototype") // // uncomment this for getting new instnace each time
// @Primary
public class OnlineOrder implements Order {

    // @Autowired
    // @Lazy
    // PaymentEntity paymentEntity;

    public OnlineOrder() {
        System.out.println("Online Order Entity Constructor "+this.hashCode());
    }

    @Override
    public String getOrder() {
        return "Online Order MEthod called";
    }

}
