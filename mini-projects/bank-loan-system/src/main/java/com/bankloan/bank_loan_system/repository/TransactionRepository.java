package com.bankloan.bank_loan_system.repository;

import com.bankloan.bank_loan_system.entity.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByLoanIdOrderByTransactionDateDesc(Long loanId);
    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.loanId = :loanId AND t.transactionType = 'EMI'")
    Double sumEMIPaymentsByLoanId(@Param("loanId") Long loanId);
}
