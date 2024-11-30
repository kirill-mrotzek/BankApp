package org.telran.bank.service;

import org.telran.bank.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAllByUser(Long userId);

    Account create(Account account);

    Account delete(long id);

    Account getById(Long id);

}
