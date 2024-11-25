package org.telran.banc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telran.banc.entity.User;
import org.telran.banc.exception.UserNotFoundException;
import org.telran.banc.repository.UserJpaRepository;

import java.util.List;

@Service
public class UserSreviceImpl implements UserService{

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public List<User> getAll() {
        return userJpaRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userJpaRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public User create(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userJpaRepository.deleteById(id);
    }
}
