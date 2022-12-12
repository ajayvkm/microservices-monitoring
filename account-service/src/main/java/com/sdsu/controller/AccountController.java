package com.sdsu.controller;


import com.sdsu.model.dto.AccountDto;
import com.sdsu.model.entity.Account;
import com.sdsu.repository.AccountRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@RefreshScope
@CrossOrigin(origins = "http://localhost:8762")
public class AccountController {

    private final AccountRepository repository;

    @Value("${msg:Config Server is not working. Please check...}")
    public String msg;

    @GetMapping("/")
    @Operation(summary = "Welcome API.")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok(this.msg);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find account by account id.")
    public ResponseEntity<Account> get(@PathVariable("id") Integer id)
    {
        try {
            Optional<Account> accountData = repository.findById(id);
            if (accountData.isPresent()) {
                log.info("Account {} detail fetched {}", id, accountData.get());
                return new ResponseEntity<>(accountData.get(), HttpStatus.OK);
            } else {
                log.warn("Account {} details not found", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Search account by id failed " + e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Get all the accounts")
    public ResponseEntity<List<Account>> getAll()
    {
        try {
            List<Account> allAccounts = repository.findAllByOrderByIdDesc();
            if (allAccounts.isEmpty()) {
                log.warn("Accounts list empty.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.info("Executing fetching all accounts {}", allAccounts);
            return new ResponseEntity<>(allAccounts, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Executing fetching all accounts failed " + e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    @Operation(summary = "Use this api to add new account.")
    public ResponseEntity<Account> addNewAccount(@RequestBody AccountDto account)
    {
        try {
            Account _account = repository.save(new Account(account.getFullName(), account.getEmail(), new Date(), "system"));
            log.info("New account added to the database {}", _account.getId());
            return new ResponseEntity<>(_account, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error occurred while saving the account", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Use this api to update any account.")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") Integer id, @RequestBody AccountDto account) {
        Optional<Account> accountData = repository.findById(id);

        if (accountData.isPresent()) {
            Account _account = accountData.get();
            _account.setFullName(account.getFullName());
            _account.setEmail(account.getEmail());
            _account.setCreatedDate(new Date());
            _account.setCreatedBy("system");
            Account savedAccount = repository.save(_account);
            log.info("Account {} updated successfully to the database.", _account.getId());
            return new ResponseEntity<>(savedAccount, HttpStatus.OK);
        } else {
            log.error("Error occurred while updating the Account {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Use this api to delete any single account.")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("id") Integer id) {
        try {
            repository.deleteById(id);
            log.info("Account {} deleted successfully from the database", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.info("Account {} failed to delete from the database", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-all")
    @Operation(summary = "Use this api to delete all account.")
    public ResponseEntity<HttpStatus> deleteAllAccounts() {
        try {
            repository.deleteAll();
            log.info("All accounts deleted successfully from the database.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("All accounts failed to delete from the database.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
