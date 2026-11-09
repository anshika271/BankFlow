
package com.bankflow.bankflow.service;

import com.bankflow.bankflow.entity.User;
import com.bankflow.bankflow.entity.Wallet;
import com.bankflow.bankflow.repository.UserRepository;
import com.bankflow.bankflow.repository.WalletRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public WalletService(
            WalletRepository walletRepository,
            UserRepository userRepository) {

        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    // Create wallet for a registered user
    public Wallet createWallet(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                    new RuntimeException("User not found!")
                );

        // Check whether user already has a wallet
        if (walletRepository.findByUser(user).isPresent()) {
            throw new RuntimeException(
                "User already has a wallet!"
            );
        }

        // Generate unique wallet number
        String walletNumber = "WAL" +
                UUID.randomUUID().toString()
                .substring(0, 8).toUpperCase();

        Wallet wallet = new Wallet(
                walletNumber,
                BigDecimal.ZERO,
                user
        );

        return walletRepository.save(wallet);
    }

    // Check wallet balance
    public Wallet getWalletByUserId(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                    new RuntimeException("User not found!")
                );

        return walletRepository.findByUser(user)
                .orElseThrow(() ->
                    new RuntimeException("Wallet not found!")
                );
    }

    // Deposit money into wallet
    @Transactional
    public Wallet depositMoney(Long userId, BigDecimal amount) {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                "Deposit amount must be greater than zero!"
            );
        }

        Wallet wallet = getWalletByUserId(userId);

        BigDecimal updatedBalance =
                wallet.getBalance().add(amount);

        wallet.setBalance(updatedBalance);

        return walletRepository.save(wallet);
    }
}
