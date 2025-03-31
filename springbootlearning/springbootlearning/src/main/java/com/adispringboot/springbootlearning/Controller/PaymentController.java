package com.adispringboot.springbootlearning.Controller;

import com.adispringboot.springbootlearning.DTO.PaymentResponse;
import com.adispringboot.springbootlearning.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adispringboot.springbootlearning.DTO.PaymentRequest;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    
    @Autowired
    PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentById(@PathVariable Long id){

        // map incoming data to internal request DTO
        PaymentRequest internalReqObj = new PaymentRequest();
        internalReqObj.setPaymentId(id);
        
        // pass this internalObj to further layer i.e Service layer
        PaymentResponse payment = paymentService.getPaymentsDetailsById(internalReqObj);

        // retrurn the repsonse DTO
        return ResponseEntity.ok(payment);

    }

}
