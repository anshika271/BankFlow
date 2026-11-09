
package com.bankflow.bankflow.repository;

import com.bankflow.bankflow.entity.Transaction;
import com.bankflow.bankflow.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {

    List<Transaction> findBySenderWalletOrReceiverWallet(
            Wallet senderWallet,
            Wallet receiverWallet
    );
}
