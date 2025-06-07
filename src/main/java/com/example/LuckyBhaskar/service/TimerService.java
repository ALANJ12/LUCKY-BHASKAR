package com.example.LuckyBhaskar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class TimerService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private WinnerService winnerService;
    @Autowired
    private GameStateService gameStateService;

//    private int currentRound = 1;
//    private LocalDateTime currentRoundStartTime = LocalDateTime.now();
    @Scheduled(fixedRate = 30000)
    public void sendTimerReset() {
        messagingTemplate.convertAndSend("/topic/timer", "TIMER_RESET");
        gameStateService.nextRound();
//        currentRound++;
//        currentRoundStartTime=LocalDateTime.now();
    }

    @Scheduled(fixedRate = 20000)
    public void sendButtonLockMsg() {

        messagingTemplate.convertAndSend("/topic/timer", "Lock");
        int evaluatingRound = gameStateService.getCurrentRoundNumber() - 1;
        winnerService.computeWinner(evaluatingRound);

    }
}
