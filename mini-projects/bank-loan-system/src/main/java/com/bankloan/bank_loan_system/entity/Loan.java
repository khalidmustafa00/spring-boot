package com.bankloan.bank_loan_system.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Loan")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // matches BIGINT PRIMARY KEY AUTO_INCREMENT

    @Column(name = "customer_id")
    private Long customerId; // Simple foreign key, not an object reference

    @Column(name = "principal_amount", nullable = false)
    private double principalAmount; // matches DOUBLE NOT NULL

    @Column(name = "interest_rate", nullable = false)
    private double interestRate; // matches DOUBLE NOT NULL

    @Column(name = "loan_period_years", nullable = false)
    private int loanPeriodYears; // matches INT NOT NULL

    @Column(name = "total_amount", nullable = false)
    private double totalAmount; // matches DOUBLE NOT NULL

    @Column(name = "emi_amount", nullable = false)
    private double emiAmount; // matches DOUBLE NOT NULL

    @Column(name = "remaining_amount", nullable = false)
    private double remainingAmount; // matches DOUBLE NOT NULL

    @Column(name = "emis_left", nullable = false)
    private int emisLeft; // matches INT NOT NULL

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
    public Loan() {}
    public Loan(Long id, Long customerId, double principalAmount, double interestRate, int loanPeriodYears, double totalAmount, double emiAmount, double remainingAmount, int emisLeft, LocalDateTime createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.principalAmount = principalAmount;
        this.interestRate = interestRate;
        this.loanPeriodYears = loanPeriodYears;
        this.totalAmount = totalAmount;
        this.emiAmount = emiAmount;
        this.remainingAmount = remainingAmount;
        this.emisLeft = emisLeft;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public double getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(double principalAmount) {
        this.principalAmount = principalAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getLoanPeriodYears() {
        return loanPeriodYears;
    }

    public void setLoanPeriodYears(int loanPeriodYears) {
        this.loanPeriodYears = loanPeriodYears;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getEmiAmount() {
        return emiAmount;
    }

    public void setEmiAmount(double emiAmount) {
        this.emiAmount = emiAmount;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public int getEmisLeft() {
        return emisLeft;
    }

    public void setEmisLeft(int emisLeft) {
        this.emisLeft = emisLeft;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}