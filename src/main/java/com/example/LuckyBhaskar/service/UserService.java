package com.example.LuckyBhaskar.service;

import com.example.LuckyBhaskar.Dto.UserDto;
import com.example.LuckyBhaskar.Enums.TransactionType;
import com.example.LuckyBhaskar.model.Transaction;
import com.example.LuckyBhaskar.model.Users;
import com.example.LuckyBhaskar.model.Wallet;
import com.example.LuckyBhaskar.repository.TransactionRepository;
import com.example.LuckyBhaskar.repository.UserRepository;
import com.example.LuckyBhaskar.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TransactionRepository transactionRepository;

    public void registerUser(UserDto request) {
        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // BCrypt
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRole("ROLE_USER");
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        depositBonus(user);
    }

    public void registerAdmin(UserDto request) {
        Users admin = new Users();
        admin.setUsername(request.getUsername());
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setName(request.getName());
        admin.setEmail(request.getEmail());
        admin.setRole("ROLE_ADMIN");
        admin.setCreatedAt(LocalDateTime.now());

        userRepository.save(admin);
    }
//to credit the bonus amount when registered
    public void depositBonus(Users user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBonusAmount(BigDecimal.valueOf(20.00));
        walletRepository.save(wallet);

        Transaction transaction =new Transaction();
        transaction.setUser(user);
        transaction.setType(TransactionType.BONUS);
        transaction.setAmount(BigDecimal.valueOf(20.00));
        transaction.setDescription("Bonus credited");
        transaction.setCreatedAt(LocalDateTime.now());
        transactionRepository.save(transaction);

    }
}
