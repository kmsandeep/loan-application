package com.kmsandeep.loanApplication.service;

import com.kmsandeep.loanApplication.constant.LoanStatus;
import com.kmsandeep.loanApplication.repository.LoanRepository;
import com.kmsandeep.loanApplication.model.Loan;
import com.kmsandeep.loanApplication.model.request.LoanRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Transactional
@Service
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
    public Optional<Loan> findLoan(String applicationId) {
        return loanRepository.findById(applicationId);
    }

    @Override
    public List<Loan> findAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan updateLoan(Loan loan) {
        return loanRepository.save(loan);
    }

//    @Override
//    public List<Loan> findByApplicantName(String name) {
//        return loanRepository.findByApplicantName(name);
//    }
}
