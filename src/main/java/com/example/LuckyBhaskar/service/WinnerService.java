package com.example.LuckyBhaskar.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class WinnerService {
    @Async
    public void computeWinner(int roundNumber){

            // Simulate time-consuming logic
            System.out.println("Started evaluating round " + roundNumber + " in: " + Thread.currentThread().getName());

//            Thread.sleep(3000); // simulate delay

            // Do real DB logic here: fetch bets, calculate winners, update balances
            System.out.println("Winner logic done for round " + roundNumber);

    }
}
