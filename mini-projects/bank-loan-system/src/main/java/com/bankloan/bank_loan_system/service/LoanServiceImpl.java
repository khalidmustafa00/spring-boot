package com.bankloan.bank_loan_system.service;

import com.bankloan.bank_loan_system.entity.Customer;
import com.bankloan.bank_loan_system.entity.Loan;
import com.bankloan.bank_loan_system.entity.Transaction;
import com.bankloan.bank_loan_system.repository.CustomerRepository;
import com.bankloan.bank_loan_system.repository.LoanRepository;
import com.bankloan.bank_loan_system.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public LoanServiceImpl(LoanRepository loanRepository,
                           TransactionRepository transactionRepository,
                           CustomerRepository customerRepository) {
        this.loanRepository = loanRepository;
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Loan createLoan(Long customerId, double principalAmount,
                           double interestRate, int loanPeriodYears) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        double interest = principalAmount * (interestRate/100) * loanPeriodYears;
        double totalAmount = principalAmount + interest;
        double emiAmount = totalAmount / (loanPeriodYears * 12);

        Loan loan = new Loan();
        loan.setCustomerId(customer.getId());
        loan.setPrincipalAmount(principalAmount);
        loan.setInterestRate(interestRate);
        loan.setLoanPeriodYears(loanPeriodYears);
        loan.setTotalAmount(totalAmount);
        loan.setEmiAmount(emiAmount);
        loan.setRemainingAmount(totalAmount);
        loan.setEmisLeft(loanPeriodYears * 12);
        loan.setCreatedAt(LocalDateTime.now());

        return loanRepository.save(loan);
    }
    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public List<Loan> getCustomerLoans(Long customerId) {
        return loanRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Loan> getActiveCustomerLoans(Long customerId) {
        return loanRepository.findByCustomerIdAndRemainingAmountGreaterThan(customerId, 0.0);
    }

    @Override
    public Loan getLoanDetails(Long loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));
    }

    @Override
    public List<Transaction> getLoanTransactions(Long loanId) {
        return transactionRepository.findByLoanIdOrderByTransactionDateDesc(loanId);
    }

    @Override
    public Loan processPayment(Long loanId, double amount,
                               Transaction.TransactionType transactionType) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));

        if (loan.getRemainingAmount() <= 0) {
            throw new IllegalStateException("Loan already paid in full");
        }

        double paymentAmount = Math.min(amount, loan.getRemainingAmount());

        Transaction transaction = new Transaction();
        transaction.setLoanId(loanId);
        transaction.setAmount(paymentAmount);
        transaction.setTransactionType(transactionType);
        transaction.setTransactionDate(LocalDateTime.now());
        transactionRepository.save(transaction);

        loan.setRemainingAmount(loan.getRemainingAmount() - paymentAmount);

        if (transactionType == Transaction.TransactionType.EMI) {
            loan.setEmisLeft(loan.getEmisLeft() - 1);
        } else {
            loan.setEmisLeft((int) Math.ceil(loan.getRemainingAmount() / loan.getEmiAmount()));
        }

        return loanRepository.save(loan);
    }
}