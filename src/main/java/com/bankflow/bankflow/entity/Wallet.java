package com.bankflow.bankflow.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name="wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,unique=true)
    private String walletNumber;
    
    @Column(nullable=false,precision = 15,scale=2)
    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name="user_id",nullable=false,unique = true)
    private User user;

    public Wallet(){

    }

    public Wallet(String walletNumber, BigDecimal balance, User user) {
        this.walletNumber = walletNumber;
        this.balance = balance;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getWalletNumber() {
        return walletNumber;
    }

    public void setWalletNumber(String walletNumber) {
        this.walletNumber = walletNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
