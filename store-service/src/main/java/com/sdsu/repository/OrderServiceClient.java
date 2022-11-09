package com.sdsu.repository;

import com.sdsu.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@Component
public class OrderServiceClient {

    @Autowired
    RestTemplate restTemplate;

    String baseUrl = "http://localhost:2224/order/";

    public Order findById(Integer orderId) {
        return null;
    }

    //@GetMapping(value = "/order/api/v1/orders")
    public List<Order> findAll() {
        List<Order> orderList = null;
        String url = baseUrl + "api/v1/orders";
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> requestEntity = new HttpEntity<>(null, headers);
        URI uri = UriComponentsBuilder.fromHttpUrl(url).build().toUri();

        ParameterizedTypeReference<Collection<Order>> typeReference = new ParameterizedTypeReference<Collection<Order>>() {
        };

        try {
            ResponseEntity<Collection<Order>> OrderCollection = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, typeReference);
            Collection<Order> accountCollection = OrderCollection.getBody();
            orderList = accountCollection.stream().collect(toCollection(ArrayList::new));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }
}
