package com.adispringboot.springbootlearning.Configuration;

import com.adispringboot.springbootlearning.Entity.OfflineOrder;
import com.adispringboot.springbootlearning.Entity.OnlineOrder;
import com.adispringboot.springbootlearning.Entity.Order;
import com.adispringboot.springbootlearning.Entity.PaymentEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
public class AppConfig {

//    @Bean
//    public PaymentEntity getPaymentEntity1(){
//        return new PaymentEntity(100L,"Adi");
//    }
//
//    @Bean
//    public PaymentEntity getPaymentEntity2(){
//        return new PaymentEntity(200L,"pani");
//    }

    @Bean
    public Order getOrderBean(@Value("${isOnlineOrder}") boolean isOnlineOrder){
        if(isOnlineOrder){
            return new OnlineOrder();
        }else{
            return new OfflineOrder();
        }
    }


}

