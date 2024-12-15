package org.telran.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
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
    public String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            return authentication.getName();
        }
        return null;
    }

    @Override
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            String name = authentication.getName();
            User userEntity = getByName(name);
            return userEntity.getId();
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }
}
