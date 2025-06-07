package com.example.LuckyBhaskar.Dto;

import java.math.BigDecimal;

public class BetDto {
    private BigDecimal amount;
    private String colour;

    public BetDto() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
