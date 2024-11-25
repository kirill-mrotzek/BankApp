package org.telran.banc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.telran.banc.entity.User;

import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
    List<User> findAll();


    User save(User user);
}
