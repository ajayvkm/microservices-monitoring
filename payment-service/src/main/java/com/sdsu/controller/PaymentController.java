package com.sdsu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sdsu.entity.Payment;
import com.sdsu.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@CrossOrigin(origins = "http://localhost:8762")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process-payment")
    public Payment doPayment(@RequestBody Payment payment) throws JsonProcessingException {
        return paymentService.processPayment(payment);
    }

    @GetMapping("/{orderId}")
    public Payment findPaymentHistoryByOrderId(@PathVariable int orderId) throws JsonProcessingException {
        return paymentService.findPaymentHistoryByOrderId(orderId);
    }
}
