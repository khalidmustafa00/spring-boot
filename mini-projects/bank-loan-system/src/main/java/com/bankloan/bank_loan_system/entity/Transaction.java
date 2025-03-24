package com.bankloan.bank_loan_system.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // matches BIGINT PRIMARY KEY AUTO_INCREMENT

    @Column(name = "loan_id")
    private Long loanId; // Simple foreign key reference

    @Column(name = "amount", nullable = false)
    private double amount; // matches DOUBLE NOT NULL

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", columnDefinition = "ENUM('EMI', 'LUMP_SUM') NOT NULL")
    private TransactionType transactionType; // matches ENUM

    @Column(name = "transaction_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime transactionDate; // matches TIMESTAMP DEFAULT

    // Enum definition
    public enum TransactionType {
        EMI, LUMP_SUM
    }

    // Constructors, getters, setters
    public Transaction() {}

    public Transaction(Long id, Long loanId, double amount, TransactionType transactionType, LocalDateTime transactionDate) {
        this.id = id;
        this.loanId = loanId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
}