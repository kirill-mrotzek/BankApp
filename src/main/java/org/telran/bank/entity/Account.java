package org.telran.bank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.telran.bank.enums.Currency;


@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private String accountNumber;

    @NotNull
    private int amount;

    @NotNull
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
        //
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public @NotNull String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(@NotNull String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @NotNull
    public int getAmount() {
        return amount;
    }

    public void setAmount(@NotNull int amount) {
        this.amount = amount;
    }

    public @NotNull Currency getCurrency() {
        return currency;
    }

    public void setCurrency(@NotNull Currency currency) {
        this.currency = currency;
    }
}
