package org.telran.bank.service;

import org.telran.bank.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getById(Long id);

    User create(User user);

    User getByName(String name);

    void delete(Long id);

    List<User> getWithEqualsPasswords(String password);

    String getCurrentUserName();

    Long getCurrentUserId();
}


