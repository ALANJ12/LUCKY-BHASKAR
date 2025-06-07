package com.example.LuckyBhaskar.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="wallet", schema = "bhaskar")
public class Wallet {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private Users user;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal bonusAmount = BigDecimal.ZERO;
public Wallet(){

}

    public Wallet(Long id, Users user, BigDecimal balance, BigDecimal bonusAmount) {
        this.id = id;
        this.user = user;
        this.balance = balance;
        this.bonusAmount = bonusAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(BigDecimal bonusAmount) {
        this.bonusAmount = bonusAmount;
    }
}
