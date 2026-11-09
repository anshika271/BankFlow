
package com.bankflow.bankflow.controller;

import com.bankflow.bankflow.dto.TransferRequest;
import com.bankflow.bankflow.entity.Transaction;
import com.bankflow.bankflow.service.TransactionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Transfer money between two wallets
    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transferMoney(
            @RequestBody TransferRequest request) {

        Transaction transaction = transactionService.transferMoney(
                request.getSenderWalletNumber(),
                request.getReceiverWalletNumber(),
                request.getAmount()
        );

        return ResponseEntity.ok(transaction);
    }

    // Get transaction history of a wallet
    @GetMapping("/{walletNumber}")
    public ResponseEntity<List<Transaction>> getTransactionHistory(
            @PathVariable String walletNumber) {

        List<Transaction> transactions =
                transactionService.getTransactionHistory(walletNumber);

        return ResponseEntity.ok(transactions);
    }
}
