package com.example.LuckyBhaskar.service;

import com.example.LuckyBhaskar.Dto.BetDto;
import com.example.LuckyBhaskar.model.Bet;
import com.example.LuckyBhaskar.model.Users;
import com.example.LuckyBhaskar.model.Wallet;
import com.example.LuckyBhaskar.repository.BetRepository;
import com.example.LuckyBhaskar.repository.TransactionRepository;
import com.example.LuckyBhaskar.repository.UserRepository;
import com.example.LuckyBhaskar.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class BetService {

    @Autowired
    GameStateService gameStateService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    WalletRepository walletRepository;

    @Autowired
    BetRepository betRepository;

    public void placeBets(BetDto betDto,String userName){
        LocalDateTime roundStart = gameStateService.getCurrentRoundStartTime();
        long secondsSinceRoundStart = Duration.between(roundStart, LocalDateTime.now()).getSeconds();

        if (secondsSinceRoundStart > 20) {  // betting allowed only in first 20 seconds
            throw new IllegalStateException("Betting time is over for this round");
        }
        Users user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Financial data not found"));
        BigDecimal bonusAmount=wallet.getBonusAmount();
        BigDecimal balanceAmount=wallet.getBalance();
        BigDecimal actualBalance;
        BigDecimal updatedBalance;
        BigDecimal updatedBonus = bonusAmount;

        if (bonusAmount.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal fivePercentBonus = bonusAmount.multiply(BigDecimal.valueOf(0.05));
            actualBalance = fivePercentBonus.add(balanceAmount);

            if (betDto.getAmount().compareTo(actualBalance) <= 0) {
                // Split the deduction
                BigDecimal fromBonus = fivePercentBonus.min(betDto.getAmount()); // Just in case bet is less than 5%
                BigDecimal fromBalance = betDto.getAmount().subtract(fromBonus);

                updatedBonus = bonusAmount.subtract(fromBonus);
                updatedBalance = balanceAmount.subtract(fromBalance);
            } else {
                throw new IllegalStateException("Insufficient balance to bet");
            }
        } else {
            actualBalance = balanceAmount;

            if (betDto.getAmount().compareTo(actualBalance) <= 0) {
                updatedBalance = balanceAmount.subtract(betDto.getAmount());
            } else {
                throw new IllegalStateException("Insufficient balance to bet");
            }
        }
//        if(bonusAmount.compareTo(BigDecimal.ZERO) > 0){
//     actualBalance = bonusAmount.multiply(BigDecimal.valueOf(0.05)).add(balanceAmount);
//            BigDecimal fivePercentBonus = bonusAmount.multiply(BigDecimal.valueOf(0.05));
//     updatedBalance=balanceAmount.subtract( bonusAmount.multiply(BigDecimal.valueOf(0.05))).subtract(betDto.getAmount()).add(fivePercentBonus);
//     updatedBonus=bonusAmount.subtract(bonusAmount.multiply(BigDecimal.valueOf(0.05)));
//        }
//        else{
//            actualBalance= balanceAmount;
//            updatedBalance=balanceAmount.subtract(betDto.getAmount());
//        }
            Bet bet = new Bet();
            bet.setUser(user);
            bet.setAmount(betDto.getAmount());
            bet.setColor(betDto.getColour());
            bet.setRoundNumber(gameStateService.getCurrentRoundNumber());
            betRepository.save(bet);
            wallet.setBalance(updatedBalance);
            wallet.setBonusAmount(updatedBonus);
            walletRepository.save(wallet);




}}
