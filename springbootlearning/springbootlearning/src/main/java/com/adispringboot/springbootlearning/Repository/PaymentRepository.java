package com.adispringboot.springbootlearning.Repository;

import com.adispringboot.springbootlearning.DTO.PaymentRequest;
import com.adispringboot.springbootlearning.Entity.PaymentEntity;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepository {

    public PaymentEntity getPaymentById(PaymentRequest request){
        PaymentEntity paymentModel = executeQuery(request);
        return paymentModel;
    }

    private PaymentEntity executeQuery(PaymentRequest request){
        // connect with DB and fetch data

        PaymentEntity payment = new PaymentEntity();
        payment.setId(request.getPaymentId());
        payment.setPaymentCurrency("INR");
        payment.setPaymentAmount(100);
        return payment;
    }



}
