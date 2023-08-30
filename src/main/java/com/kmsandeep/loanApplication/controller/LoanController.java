package com.kmsandeep.loanApplication.controller;

import com.kmsandeep.loanApplication.constant.LoanStatus;
import com.kmsandeep.loanApplication.model.Loan;
import com.kmsandeep.loanApplication.model.request.LoanRequest;
import com.kmsandeep.loanApplication.model.request.LoanStatusUpdate;
import com.kmsandeep.loanApplication.model.response.LoanResponse;
import com.kmsandeep.loanApplication.errorhandler.LoanNotFoundException;
import com.kmsandeep.loanApplication.service.LoanService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/loan")
public class LoanController {
    private final List<Loan> loans = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(LoanController.class);
    @Autowired
    private LoanService loanService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping
    public ResponseEntity<LoanResponse> submitLoan(@RequestBody @Valid LoanRequest request) {
        Loan application = loanService.submitLoan(request);
        loans.add(application);
        StringBuilder sb = new StringBuilder();
        kafkaTemplate.send("loan", application.toString())
                .whenComplete((res, err) -> {
                    LOGGER.info("kafka message sent success: "+ application.getId());
                });
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(LoanResponse.result(application));
    }

    @GetMapping("/find/{applicationId}")
    public ResponseEntity<LoanResponse> getLoan(@PathVariable String applicationId) {
        Optional<Loan> optionalApplication = loanService.findLoan(applicationId);
        if (optionalApplication.isPresent()) {
            return ResponseEntity.ok(LoanResponse.result(optionalApplication.get()));
        } else {
            throw new LoanNotFoundException(applicationId);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<LoanResponse> listLoans() {
        List<Loan> allLoans = loanService.findAllLoans();
        if (CollectionUtils.isEmpty(allLoans)) {
            throw new LoanNotFoundException();
        }
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(LoanResponse.result(allLoans));
    }

    @PatchMapping("/status/{applicationId}")
    public ResponseEntity<LoanResponse> updateLoanStatus(@PathVariable String applicationId, @RequestBody LoanStatusUpdate statusUpdate) {
        Optional<Loan> loanOptional = loanService.findLoan(applicationId);
        if (loanOptional.isPresent()) {
            Loan application = loanOptional.get();
            application.setStatus(statusUpdate.getLoanStatus());
            if (LoanStatus.APPROVED.equals(statusUpdate.getLoanStatus())) {
                application.setLoanApprovedAmount(statusUpdate.getLoanApprovedAmount());
            }
            return ResponseEntity.status(HttpStatus.OK).body(LoanResponse.result(application));
        } else {
            throw new LoanNotFoundException(applicationId);
        }
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "<h3> Welcome to loan application. </h3>";
    }

}
