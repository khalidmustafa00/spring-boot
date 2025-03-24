package com.bankloan.bank_loan_system.controller;

import com.bankloan.bank_loan_system.entity.Loan;
import com.bankloan.bank_loan_system.entity.Transaction;
import com.bankloan.bank_loan_system.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/new")
    public String showLoanForm(Model model) {
        model.addAttribute("customerId", ""); // Empty customer ID field
        model.addAttribute("principalAmount", "");
        model.addAttribute("interestRate", "");
        model.addAttribute("loanPeriodYears", "");
        return "lend-loan";
    }

    @PostMapping("/details")
    public String createLoan(
            @RequestParam Long customerId,
            @RequestParam double principalAmount,
            @RequestParam double interestRate,
            @RequestParam int loanPeriodYears,
            Model model) {

        Loan loan = loanService.createLoan(customerId, principalAmount, interestRate, loanPeriodYears);
        model.addAttribute("loan", loan);
        return "loan-details";
    }

    @GetMapping("/all_loans")
    public String getCustomerLoans(
            @RequestParam(required = false) Long customerId,
            Model model) {

        List<Loan> loans;
        if (customerId != null) {
            loans = loanService.getCustomerLoans(customerId);
        } else {
            loans = loanService.getAllLoans(); // You'll need to implement this
        }

        model.addAttribute("loans", loans);
        return "account-overview";
    }

    @GetMapping("/{id}")
    public String getLoanDetails(@PathVariable Long id, Model model) {
        Loan loan = loanService.getLoanDetails(id);
        List<Transaction> transactions = loanService.getLoanTransactions(id);
        model.addAttribute("loan", loan);
        model.addAttribute("transactions", transactions);
        return "ledger";
    }

    @GetMapping("/{id}/pay")
    public String showPaymentForm(@PathVariable Long id, Model model) {
        Loan loan = loanService.getLoanDetails(id);
        model.addAttribute("loan", loan);
        return "payment";
    }

    @PostMapping("/{id}/pay")
    public String processPayment(
            @PathVariable Long id,
            @RequestParam double amount,
            @RequestParam Transaction.TransactionType transactionType) {

        loanService.processPayment(id, amount, transactionType);
        return "redirect:/loans/" + id;
    }
}
