package com.sdsu.repository;

import com.sdsu.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@Component
public class AccountServiceClient {

    String baseUrl = "http://localhost:2225/account/";

    @Autowired
    RestTemplate restTemplate;

    public Account findById(@PathVariable("accountId") Integer accountId) {
        ResponseEntity<Account> response = restTemplate.getForEntity(baseUrl+"api/v1/accounts/"+accountId, Account.class);
        Account account = response.getBody();
        return account;
    }

    List<Account> findAll() {
        List<Account> accountList = null;
        String url = baseUrl + "api/v1/accounts";
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> requestEntity = new HttpEntity<>(null, headers);
        URI uri = UriComponentsBuilder.fromHttpUrl(url).build().toUri();


        ParameterizedTypeReference<Collection<Account>> typeReference = new ParameterizedTypeReference<Collection<Account>>() {
        };
        try {
            ResponseEntity<Collection<Account>> accountsCollection = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, typeReference);
            Collection<Account> accountCollection = accountsCollection.getBody();
            accountList = accountCollection.stream().collect(toCollection(ArrayList::new));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountList;
    }
}
