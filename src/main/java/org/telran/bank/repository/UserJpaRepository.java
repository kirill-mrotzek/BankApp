package org.telran.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.telran.bank.entity.User;

import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
    List<User> findAll();


    User save(User user);

    List<User> findAllByPassword(String password);
}
