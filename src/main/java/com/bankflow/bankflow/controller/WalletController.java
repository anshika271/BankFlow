
package com.bankflow.bankflow.controller;

import com.bankflow.bankflow.entity.Wallet;
import com.bankflow.bankflow.service.WalletService;
import com.bankflow.bankflow.dto.DepositRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    // Create wallet for a user
    @PostMapping("/{userId}")
    public ResponseEntity<Wallet> createWallet(
            @PathVariable Long userId) {

        Wallet wallet = walletService.createWallet(userId);
        return ResponseEntity.ok(wallet);
    }

    // Get wallet using user ID
    @GetMapping("/{userId}")
    public ResponseEntity<Wallet> getWallet(
            @PathVariable Long userId) {

        Wallet wallet = walletService.getWalletByUserId(userId);
        return ResponseEntity.ok(wallet);
    }

    // Deposit money into wallet
    @PostMapping("/{userId}/deposit")
    public ResponseEntity<Wallet> depositMoney(
            @PathVariable Long userId,
            @RequestBody DepositRequest request) {

        Wallet wallet = walletService.depositMoney(
                userId, request.getAmount());

        return ResponseEntity.ok(wallet);
    }
}
