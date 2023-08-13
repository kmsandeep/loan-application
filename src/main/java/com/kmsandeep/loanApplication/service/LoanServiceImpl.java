package com.kmsandeep.loanApplication.service;

import com.kmsandeep.loanApplication.constant.LoanStatus;
import com.kmsandeep.loanApplication.dao.LoanRepository;
import com.kmsandeep.loanApplication.dto.Loan;
import com.kmsandeep.loanApplication.dto.request.LoanRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class LoanServiceImpl implements LoanService{
    @Autowired
    LoanRepository loanRepository;
    @Override
    public Loan submitLoan(LoanRequest loanRequest) {
        Loan loan = LoanServiceUtils.mapRequestToLoan(loanRequest);
        loan.setStatus(LoanStatus.PENDING);
        Loan saved = loanRepository.save(loan);
        return saved;
    }

    @Override
    public Loan findLoan(String applicationId) {
        Optional<Loan> loanOptional = loanRepository.findById(applicationId);
        return loanOptional.get();
    }

    @Override
    public List<Loan> findAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan updateLoan(Loan loan) {
        return loanRepository.save(loan);
    }
}
