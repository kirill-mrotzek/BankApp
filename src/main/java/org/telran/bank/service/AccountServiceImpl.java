package org.telran.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telran.bank.entity.Account;
import org.telran.bank.exception.AccountNotFoundException;
import org.telran.bank.repository.AccountJpaRepository;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountJpaRepository accountJpaRepository;

    @Autowired
    private  UserService userService;

    @Override
    public List<Account> getAllByUser() {
        Long currentUserId = userService.getCurrentUserId();
        return accountJpaRepository.findByUserId(currentUserId);
    }

    @Override
    public Account create(Account account) {
        if (account.getId() != null && accountJpaRepository.existsById(account.getId())) {
            throw new AccountNotFoundException("Account with id " + account.getId() + " already exists");
        }
        return accountJpaRepository.save(account);
    }

    @Override
    public Account delete(long id) {
        Account account = accountJpaRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account with id " + id + " not found"));
        accountJpaRepository.delete(account);
        return account;
    }

    // Метод для получения аккаунта по id
    @Override
    public Account getById(Long id) {
        return accountJpaRepository
                .findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account with id " + id + " not found"));
    }

    @Override
    public List<Account> getAccountsByUserName(String currentUserName) {
        return List.of();
    }
}