package com.kmsandeep.loanApplication.dao;

import com.kmsandeep.loanApplication.dto.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan,String> {

}
