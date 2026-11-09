
package com.bankflow.bankflow.service;

import com.bankflow.bankflow.entity.Transaction;
import com.bankflow.bankflow.entity.Wallet;
import com.bankflow.bankflow.repository.TransactionRepository;
import com.bankflow.bankflow.repository.WalletRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(
            WalletRepository walletRepository,
            TransactionRepository transactionRepository) {

        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    // Transfer money between two wallets
    @Transactional
    public Transaction transferMoney(
            String senderWalletNumber,
            String receiverWalletNumber,
            BigDecimal amount) {

        // Validate amount
        if (amount == null ||
                amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Transfer amount must be greater than zero!"
            );
        }

        // Find sender wallet
        Wallet sender = walletRepository
                .findByWalletNumber(senderWalletNumber)
                .orElseThrow(() ->
                        new RuntimeException("Sender wallet not found!")
                );

        // Find receiver wallet
        Wallet receiver = walletRepository
                .findByWalletNumber(receiverWalletNumber)
                .orElseThrow(() ->
                        new RuntimeException("Receiver wallet not found!")
                );

        // Prevent transferring money to the same wallet
        if (sender.getId().equals(receiver.getId())) {
            throw new IllegalArgumentException(
                    "Sender and receiver wallets must be different!"
            );
        }

        // Check sufficient balance
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException(
                    "Insufficient wallet balance!"
            );
        }

        // Deduct money from sender
        sender.setBalance(sender.getBalance().subtract(amount));

        // Add money to receiver
        receiver.setBalance(receiver.getBalance().add(amount));

        // Save updated wallet balances
        walletRepository.save(sender);
        walletRepository.save(receiver);

        // Create transaction record
        Transaction transaction = new Transaction(
                sender,
                receiver,
                amount,
                "SUCCESS",
                LocalDateTime.now()
        );

        // Save transaction in database
        return transactionRepository.save(transaction);
    }

    // Get transaction history for a wallet
    public List<Transaction> getTransactionHistory(
            String walletNumber) {

        Wallet wallet = walletRepository
                .findByWalletNumber(walletNumber)
                .orElseThrow(() ->
                        new RuntimeException("Wallet not found!")
                );

        return transactionRepository
                .findBySenderWalletOrReceiverWallet(wallet, wallet);
    }
}
