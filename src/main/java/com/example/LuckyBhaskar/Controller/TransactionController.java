package com.example.LuckyBhaskar.Controller;

import com.example.LuckyBhaskar.Dto.TransactionRequestDto;
import com.example.LuckyBhaskar.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transaction")
    public ResponseEntity<String> handleTransaction(@RequestBody TransactionRequestDto transactionRequestDto , Authentication authentication){

        String UserName =authentication.getName();
        transactionService.handleTransaction(transactionRequestDto,UserName);
        return ResponseEntity.ok("Transaction successful");
    }

}
