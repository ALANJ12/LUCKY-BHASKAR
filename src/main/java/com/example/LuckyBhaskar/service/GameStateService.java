package com.example.LuckyBhaskar.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class GameStateService {
    private int currentRoundNumber = 1;
    private LocalDateTime currentRoundStartTime = LocalDateTime.now();

    // Called by TimerService every 30 seconds to start new round
    public synchronized void nextRound() {
        currentRoundNumber++;
        currentRoundStartTime = LocalDateTime.now();
        System.out.println("Round updated: " + currentRoundNumber + " at " + currentRoundStartTime);
    }

    public int getCurrentRoundNumber() {
        return currentRoundNumber;
    }

    public LocalDateTime getCurrentRoundStartTime() {
        return currentRoundStartTime;
    }
}
