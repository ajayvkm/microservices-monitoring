package com.sdsu.repository;

import com.sdsu.dto.OrderRequest;
import com.sdsu.dto.TransactionResponse;
import com.sdsu.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@Component
@Slf4j
public class OrderServiceClient {

    @Autowired
    RestTemplate restTemplate;

    String baseUrl = "http://order-service/order/";

    public Order findById(Integer orderId) {
        return null;
    }

    public List<Order> findAll() {
        List<Order> orderList = null;
        String url = baseUrl + "api/v1/orders/all";
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
            log.error(e.getMessage());
        }
        return orderList;
    }

    public ResponseEntity<TransactionResponse> placeOrder(final OrderRequest order) {
        String url = baseUrl + "api/v1/orders/book";
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> requestEntity = new HttpEntity<>(order, headers);
        URI uri = UriComponentsBuilder.fromHttpUrl(url).build().toUri();

        try {
            ResponseEntity<TransactionResponse> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, TransactionResponse.class);
            return responseEntity;
        } catch (Exception e) {
            log.error("Failed to place order {}", e.getMessage());
        }
        return null;
    }
}
