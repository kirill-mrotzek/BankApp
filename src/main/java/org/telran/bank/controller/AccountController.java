package org.telran.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.telran.bank.entity.Account;
import org.telran.bank.service.AccountService;
import org.telran.bank.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')") //when role starts with ROLE_
    //@PreAuthorize("hasAnyAuthority('ADMIN')") //when role starts without ROLE_
    public List<Account> getAllByUser() {
        return accountService.getAllByUser();
    }

    @GetMapping("/current")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    //@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Account getById(@PathVariable (name = "id") Long id) {
        Long currentUserId = userService.getCurrentUserId();
        return null;
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

