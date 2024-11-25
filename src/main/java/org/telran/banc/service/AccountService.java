package org.telran.banc.service;

import org.telran.banc.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAllByUser(Long userId);

    Account create(Account account);

    Account delete(long id);

    Account getById(Long id);

}
