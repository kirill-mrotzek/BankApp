package org.telran.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.telran.bank.entity.Account;

import java.util.List;

@Repository
public interface AccountJpaRepository extends JpaRepository<Account, Long> {

    List<Account> findByUserId(Long userId);


}
