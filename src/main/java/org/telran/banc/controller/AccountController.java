package org.telran.banc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.telran.banc.entity.Account;
import org.telran.banc.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/user/{userId}")
    public List<Account> getAllByUser(@PathVariable Long userId) {
        return accountService.getAllByUser(userId);
    }

    @GetMapping
    public Account getBiId(@PathVariable (name = "id") Long id) {
        return accountService.getById(id);
    }

    @PostMapping
    public Account create (@RequestBody Account account){
        return accountService.create(account);
    }

    @DeleteMapping("/{id}")
    public Account delete (@PathVariable Long id){
        return accountService.delete(id);
    }
}

