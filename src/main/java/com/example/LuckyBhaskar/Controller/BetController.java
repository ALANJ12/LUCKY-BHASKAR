package com.example.LuckyBhaskar.Controller;

import com.example.LuckyBhaskar.Dto.BetDto;
import com.example.LuckyBhaskar.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BetController
{
    @Autowired
    BetService betService;
@PostMapping("/Bet")
    public ResponseEntity<String> PlaceBet(@RequestBody BetDto betDto, Authentication authentication){
    String UserName=authentication.getName();
    betService.placeBets(betDto,UserName);
    return  null;
}

}


