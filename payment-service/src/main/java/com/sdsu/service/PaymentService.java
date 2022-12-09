package com.sdsu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdsu.entity.Payment;
import com.sdsu.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    Logger logger= LoggerFactory.getLogger(PaymentService.class);

    public Payment processPayment(Payment payment) throws JsonProcessingException {
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        logger.info("Payment-Service process payment : {}",new ObjectMapper().writeValueAsString(payment));

        return repository.save(payment);
    }


    public String paymentProcessing(){
        /**
         * Third party payment gateway can be integrated here
         */
        return new Random().nextBoolean()?"success":"false";
    }


    public Payment findPaymentHistoryByOrderId(int orderId) throws JsonProcessingException {
        Payment payment= repository.findByOrderId(orderId);
        logger.info("Payment Service History - findPaymentHistoryByOrderId : {}", new ObjectMapper().writeValueAsString(payment));
        return payment ;
    }
}
