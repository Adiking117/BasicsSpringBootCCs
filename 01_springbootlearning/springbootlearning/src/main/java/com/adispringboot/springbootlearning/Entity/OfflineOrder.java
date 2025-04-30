package com.adispringboot.springbootlearning.Entity;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Qualifier("offlineOrder")
//@Component
public class OfflineOrder implements  Order{
    public OfflineOrder() {
        System.out.println("Offline Order Entity Constructor "+this.hashCode());
    }

    @Override
    public String getOrder() {
        return "Offline Order MEthod called";
    }
}
