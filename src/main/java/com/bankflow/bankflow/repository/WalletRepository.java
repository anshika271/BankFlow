
package com.bankflow.bankflow.repository;

import com.bankflow.bankflow.entity.Wallet;
import com.bankflow.bankflow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByUser(User user);

    Optional<Wallet> findByWalletNumber(String walletNumber);

    boolean existsByWalletNumber(String walletNumber);
}
