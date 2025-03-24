package com.bankloan.bank_loan_system.repository;
import com.bankloan.bank_loan_system.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByCustomerId(Long customerId);
    List<Loan> findByCustomerIdAndRemainingAmountGreaterThan(Long customerId, double amount);
}