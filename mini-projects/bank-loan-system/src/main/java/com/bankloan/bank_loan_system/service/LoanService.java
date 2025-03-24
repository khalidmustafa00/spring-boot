package com.bankloan.bank_loan_system.service;

import com.bankloan.bank_loan_system.entity.Loan;
import com.bankloan.bank_loan_system.entity.Transaction;

import java.util.List;

public interface LoanService {
        List<Loan> getAllLoans(); // Add this
        // ... existing methods

    Loan createLoan(Long customerId, double principalAmount, double interestRate, int loanPeriodYears);
    List<Loan> getCustomerLoans(Long customerId);
    List<Loan> getActiveCustomerLoans(Long customerId);
    Loan getLoanDetails(Long loanId);
    List<Transaction> getLoanTransactions(Long loanId);
    Loan processPayment(Long loanId, double amount, Transaction.TransactionType transactionType);
}