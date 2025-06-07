package com.example.LuckyBhaskar.repository;

import com.example.LuckyBhaskar.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

}
