package com.kmsandeep.loanApplication.controller;

import com.kmsandeep.loanApplication.constant.LoanStatus;
import com.kmsandeep.loanApplication.dto.Loan;
import com.kmsandeep.loanApplication.dto.request.LoanRequest;
import com.kmsandeep.loanApplication.dto.request.LoanStatusUpdate;
import com.kmsandeep.loanApplication.dto.response.LoanResponse;
import com.kmsandeep.loanApplication.errorhandler.LoanNotFoundException;
import com.kmsandeep.loanApplication.service.LoanService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        Optional<Loan> optionalApplication = loanService.findLoan(applicationId);
        if (optionalApplication.isPresent()) {
            return ResponseEntity.ok(LoanResponse.one(optionalApplication.get()));
        } else {
            throw new LoanNotFoundException(applicationId);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<LoanResponse> listLoans() {
        List<Loan> allLoans = loanService.findAllLoans();
        if(CollectionUtils.isEmpty(allLoans)){
           throw new LoanNotFoundException();
        }
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(LoanResponse.multi(allLoans));
    }

    @GetMapping
    public ResponseEntity<LoanResponse> listApplicantLoans(@RequestParam("applicantName") String name){
        List<Loan> allLoans = loanService.findByApplicantName(name);
        if(CollectionUtils.isEmpty(allLoans)){
            throw new LoanNotFoundException();
        }
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(LoanResponse.multi(allLoans));
    }

    @PatchMapping("/{applicationId}/status")
    public ResponseEntity<LoanResponse> updateLoanStatus(@PathVariable String applicationId, @RequestBody LoanStatusUpdate statusUpdate) {
        Optional<Loan> optionalApplication = loanService.findLoan(applicationId);
        if (optionalApplication.isPresent()) {
            Loan application = optionalApplication.get();
            application.setStatus(statusUpdate.getLoanStatus());
            if (LoanStatus.APPROVED.equals(statusUpdate.getLoanStatus())) {
                application.setLoanApprovedAmount(statusUpdate.getLoanApprovedAmount());
            }
            return ResponseEntity.status(HttpStatus.OK).body(LoanResponse.one(application));
        } else {
            throw new LoanNotFoundException(applicationId);
        }
    }
}
