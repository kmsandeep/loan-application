package com.kmsandeep.loanApplication.repository;

import com.kmsandeep.loanApplication.dto.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan,String> {
    public List<Loan> findByApplicantName(String applicantName);

}
