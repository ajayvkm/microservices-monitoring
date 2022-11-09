package com.sdsu.controller;


import com.sdsu.model.Account;
import com.sdsu.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountRepository repository;

    @GetMapping("/")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome to account service !");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> get(@PathVariable("id") Integer id)
    {
        final Account account = repository.findById(id).get();
        log.info("Account {} detail fetched {}", id, account);
        return ResponseEntity.ok(account);
    }

    @GetMapping()
    public ResponseEntity<Collection<Account>> getAll()
    {
        final Collection<Account> accounts = (repository.findAll().get());
        log.info("Executing fetching all accounts {}", accounts);
        return ResponseEntity.ok(accounts);
    }
}
