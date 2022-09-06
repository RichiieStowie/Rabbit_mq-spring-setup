package com.example.rabbitmq_setup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SendEmailService {
    @RabbitListener(queues = "q.send-email")
    public void sendEmail(Customer event){
        log.info("Sending email to {} ", event.getEmail());
    }
}
