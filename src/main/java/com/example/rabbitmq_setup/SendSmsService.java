package com.example.rabbitmq_setup;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SendSmsService {
    @RabbitListener(queues = "q.send-sms")
    public void sendSms(Customer event){
        log.info("Sending sms to {} ", event.getFirstname());
    }
}
