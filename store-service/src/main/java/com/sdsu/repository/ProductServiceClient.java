package com.sdsu.repository;

import static java.util.stream.Collectors.toCollection;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

import com.sdsu.model.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProductServiceClient {
    @Autowired
    RestTemplate restTemplate;

    String baseUrl = "http://product-service/product/";

    public Product findById(Integer productId) {
        Product product = null;
        try {
            ResponseEntity<Product> response = restTemplate.getForEntity(baseUrl+"api/v1/products/"+productId, Product.class);
            if(null != response && response.getStatusCode().is2xxSuccessful() && null != response.getBody()) {
                product = response.getBody();
            }
        } catch (Exception e) {
            log.error("Failed to get product details for productId {}", productId, e);
            log.error(e.getMessage());
        }
        return product;
    }

    public List<Product> findAll() {
        List<Product> productList = null;
        String url = baseUrl + "api/v1/products/all";
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> requestEntity = new HttpEntity<>(null, headers);
        URI uri = UriComponentsBuilder.fromHttpUrl(url).build().toUri();

        ParameterizedTypeReference<Collection<Product>> typeReference = new ParameterizedTypeReference<Collection<Product>>() {
        };

        try {
            ResponseEntity<Collection<Product>> productCollectionResponse = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, typeReference);
            Collection<Product> productCollection = productCollectionResponse.getBody();
            productList = productCollection.stream().collect(toCollection(ArrayList::new));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }
}
