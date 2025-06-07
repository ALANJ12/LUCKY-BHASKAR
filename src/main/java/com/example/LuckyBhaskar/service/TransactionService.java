package com.example.LuckyBhaskar.service;

import com.example.LuckyBhaskar.Dto.TransactionRequestDto;
import com.example.LuckyBhaskar.Enums.TransactionType;
import com.example.LuckyBhaskar.model.Transaction;
import com.example.LuckyBhaskar.model.Users;
import com.example.LuckyBhaskar.model.Wallet;
import com.example.LuckyBhaskar.repository.TransactionRepository;
import com.example.LuckyBhaskar.repository.UserRepository;
import com.example.LuckyBhaskar.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class TransactionService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    WalletRepository walletRepository;

    public void handleTransaction(TransactionRequestDto transactionRequestDto, String userName) {
        Users user=userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Financial data not found"));
        Transaction transaction=new Transaction();
        wallet.setUser(user);
        transaction.setUser(user);

        BigDecimal amount =transactionRequestDto.getAmount();
        TransactionType type=TransactionType.valueOf(transactionRequestDto.getType());
        if(type!=null && type==TransactionType.DEPOSIT){
            transaction.setCreatedAt(LocalDateTime.now());
            transaction.setDescription("Deposited");
            transaction.setAmount(transactionRequestDto.getAmount());
            transaction.setType(TransactionType.DEPOSIT);
            transactionRepository.save(transaction);
            wallet.setBalance(wallet.getBalance().add(transactionRequestDto.getAmount()));
            walletRepository.save(wallet);
        } else if (type!=null && type==TransactionType.WITHDRAWAL) {

             if (wallet.getBalance().compareTo(amount) < 0) {
                throw new RuntimeException("Insufficient balance");
            }
            wallet.setBalance(wallet.getBalance().subtract(transactionRequestDto.getAmount()));
            transaction.setCreatedAt(LocalDateTime.now());
            transaction.setDescription("Withdrew");
            transaction.setAmount(transactionRequestDto.getAmount());
            transaction.setType(TransactionType.WITHDRAWAL);
            transactionRepository.save(transaction);
            walletRepository.save(wallet);
        }


    }
}

