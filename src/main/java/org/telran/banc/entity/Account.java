package org.telran.banc.entity;

import jakarta.persistence.*;
import org.telran.banc.enums.Currency;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private String accountNumber;

    private int amount;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    public Account(Long id, User user, String accountNumber, int amount, Currency currency) {
        this.id = id;
        this.user = user;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.currency = currency;
    }

    public Account() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
