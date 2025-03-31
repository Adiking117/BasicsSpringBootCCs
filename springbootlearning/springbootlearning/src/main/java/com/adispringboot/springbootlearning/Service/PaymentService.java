package com.adispringboot.springbootlearning.Service;

import com.adispringboot.springbootlearning.DTO.PaymentRequest;
import com.adispringboot.springbootlearning.DTO.PaymentResponse;
import com.adispringboot.springbootlearning.Entity.PaymentEntity;
import com.adispringboot.springbootlearning.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public PaymentResponse getPaymentsDetailsById(PaymentRequest internalReqObj){

        PaymentEntity paymentModel = paymentRepository.getPaymentById(internalReqObj);

        // map it to respons eobj
        PaymentResponse paymentResponse = mapModelToResponseDTO(paymentModel);
        return paymentResponse;
    }

    private PaymentResponse mapModelToResponseDTO(PaymentEntity paymentEntity){
        PaymentResponse response = new PaymentResponse();

        response.setPaymentId(paymentEntity.getId());
        response.setAmount(paymentEntity.getPaymentAmount());
        response.setCurrency(paymentEntity.getPaymentCurrency());
        return response;
    }


}
