package com.kmsandeep.loanApplication.messaging.listener;

import lombok.extern.java.Log;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(id = "loan",topics = "loan")
@Log
public class LoanKafkaListener {
    @KafkaHandler
    public void submitLoanListener(String message){
        log.info(message.substring(0,44));
    }

}
