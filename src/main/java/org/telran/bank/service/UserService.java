package org.telran.bank.service;

import org.telran.bank.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getById(Long id);

    User create(User user);

    void delete(Long id);
}
