package com.kmsandeep.loanApplication;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kmsandeep.loanApplication.controller.LoanController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.kmsandeep.loanApplication.repository" )
public class LoanApplication {
	@Bean
	ObjectMapper objectMapper(){
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return objectMapper;
	}
	private static Logger logger = LoggerFactory.getLogger(LoanApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
		logger.debug("LoanApplication - SpringBootApplication");
	}
}
