package org.telran.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.telran.bank.entity.Account;
import org.telran.bank.entity.User;

import java.util.List;

@Repository
public interface AccountJpaRepository extends JpaRepository<Account, Long> {

    List<Account> findByUserId(Long userId);


    List<Account> findByUser(User user);
}
