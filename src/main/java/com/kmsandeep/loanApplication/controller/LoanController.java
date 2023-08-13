package com.kmsandeep.loanApplication.controller;

import com.kmsandeep.loanApplication.constant.LoanStatus;
import com.kmsandeep.loanApplication.dto.Loan;
import com.kmsandeep.loanApplication.dto.request.LoanStatusUpdate;
import com.kmsandeep.loanApplication.dto.request.LoanRequest;
import com.kmsandeep.loanApplication.dto.response.LoanResponse;
import com.kmsandeep.loanApplication.errorhandler.LoanNotFoundException;
import com.kmsandeep.loanApplication.service.LoanService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/loan-api")
public class LoanController {
    private final List<Loan> loans = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanController.class);
    @Autowired
    private LoanService loanService;
    @PostMapping
    public ResponseEntity<LoanResponse> submitLoan(@RequestBody @Valid LoanRequest request) {
        LOGGER.debug("inside submitLoan request: {}",request.toString());
            Loan application = loanService.submitLoan(request);
            loans.add(application);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(LoanResponse.one(application));
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<LoanResponse> getLoan(@PathVariable String applicationId) {
        Optional<Loan> optionalApplication = loans.stream()
                .filter(application -> application.getApplicationId().equals(applicationId))
                .findFirst();
        if (optionalApplication.isPresent()) {
            return ResponseEntity.ok(LoanResponse.one(optionalApplication.get()));
        } else {
            throw new LoanNotFoundException(applicationId);
        }
    }

    @GetMapping
    public ResponseEntity<LoanResponse> listLoans() {
        return ResponseEntity.ok(LoanResponse.multi(loans));
    }

    @PatchMapping("/{applicationId}/status")
    public ResponseEntity<LoanResponse> updateLoanStatus(@PathVariable String applicationId, @RequestBody LoanStatusUpdate statusUpdate) {
        Optional<Loan> optionalApplication = loans.stream()
                .filter(application -> application.getApplicationId().equals(applicationId))
                .findFirst();

        if (optionalApplication.isPresent()) {
            Loan application = optionalApplication.get();
            application.setStatus(statusUpdate.getLoanStatus());
            if (LoanStatus.APPROVED.equals(statusUpdate.getLoanStatus())) {
                application.setLoanApprovedAmount(statusUpdate.getLoanApprovedAmount());
            }

            return ResponseEntity.ok(LoanResponse.one(application));
        } else {
            throw new LoanNotFoundException(applicationId);
        }
    }
}
