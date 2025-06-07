package com.example.LuckyBhaskar.Dto;

import java.math.BigDecimal;

public class TransactionRequestDto {
    private BigDecimal amount;
    private String type;

    public TransactionRequestDto() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
