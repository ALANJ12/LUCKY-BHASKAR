package com.example.LuckyBhaskar.repository;

import com.example.LuckyBhaskar.model.Users;
import com.example.LuckyBhaskar.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
    Optional<Wallet> findByUser(Users user);
}
