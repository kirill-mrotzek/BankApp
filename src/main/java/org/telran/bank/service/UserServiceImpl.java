package org.telran.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telran.bank.entity.User;
import org.telran.bank.exception.UserNotFoundException;
import org.telran.bank.repository.UserJpaRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserJpaRepository jpaRepository;

    @Override
    public List<User> getAll() {
        return jpaRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return jpaRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public User create(User user) {
        User save = jpaRepository.save(user);
        return save;
    }

    @Override
    public User getByName(String name) {
        return null;
    }

    @Override
    public List<User> getWithEqualsPasswords(String password) {
       return jpaRepository.findAllByPassword(password);
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }
}
