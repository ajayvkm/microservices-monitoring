package com.sdsu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdsu.dto.Payment;
import com.sdsu.dto.TransactionRequest;
import com.sdsu.dto.TransactionResponse;
import com.sdsu.model.entity.Order;
import com.sdsu.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RefreshScope
public class OrderService {
    Logger logger= LoggerFactory.getLogger(OrderService.class);
    @Autowired
    OrderRepository repository;

    String baseUrl = "http://payment-service/payment/";

    @Autowired
    RestTemplate restTemplate;

    public TransactionResponse saveOrder(TransactionRequest request) throws JsonProcessingException {
        String response = "";
        Order order = request.getOrder();
        String url = baseUrl + "payment/process-payment";
        Payment payment = request.getPayment();
        payment.setAmount(order.getTotalPrice());
        //rest call

        repository.save(order);
        payment.setOrderId(order.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> requestEntity = new HttpEntity<>(payment, headers);
        URI uri = UriComponentsBuilder.fromHttpUrl(url).build().toUri();

        logger.info("Order-Service Request : "+new ObjectMapper().writeValueAsString(request));
        ResponseEntity<Payment> paymentResponse = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, Payment.class);
        Payment paymentR = paymentResponse.getBody();
        order.setPayment(paymentR);
        if(paymentR.getPaymentStatus().equals("success")) {
            response = "payment processing successful and order placed";
        } else {
            order.setStatus("failed");
            repository.save(order);
            response = "there is a failure in payment api , order added to cart";
        }
        order.setPaymentStatusMessage(response);
        logger.info("Order Service getting Response from Payment-Service : "+new ObjectMapper().writeValueAsString(response));
        return new TransactionResponse(order);
    }


    public Payment getPayment(int orderId) {
        String url = baseUrl + "payment/" + orderId;

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        URI uri = UriComponentsBuilder.fromHttpUrl(url).build().toUri();
        ResponseEntity<Payment> paymentResponse = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, Payment.class);
        return paymentResponse.getBody();
    }
}
