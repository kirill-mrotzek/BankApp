package org.telran.banc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telran.banc.entity.Account;
import org.telran.banc.exception.AccountNotFoundException;
import org.telran.banc.exception.UserNotFoundException;
import org.telran.banc.repository.AccountJpaRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountJpaRepository accountJpaRepository;

    @Override
    public List<Account> getAllByUser(Long userId) {
        return accountJpaRepository.findByUserId(userId);
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
        return accountJpaRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account with id " + id + " not found"));
    }
}