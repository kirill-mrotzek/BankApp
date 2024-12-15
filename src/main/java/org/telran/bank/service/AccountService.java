package org.telran.bank.service;

import org.telran.bank.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAllByUser();

    Account create(Account account);

    Account delete(long id);

    Account getById(Long id);

    List<Account> getAccountsByUserName(String currentUserName);
}
