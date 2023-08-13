package com.kmsandeep.loanApplication;

import com.kmsandeep.loanApplication.controller.LoanController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoanApplication {

	private static Logger logger = LoggerFactory.getLogger(LoanApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
		logger.debug(" LoanApplication ::::: SpringBootApplication");
	}

}
