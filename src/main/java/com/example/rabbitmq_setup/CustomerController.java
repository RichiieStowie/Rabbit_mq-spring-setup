package com.example.rabbitmq_setup;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    private final CustomerServiceImpl customerService;
    @Autowired
    private final RabbitTemplate rabbitTemplate;

    public CustomerController(CustomerServiceImpl customerService, RabbitTemplate rabbitTemplate) {
        this.customerService = customerService;
        this.rabbitTemplate = rabbitTemplate;
    }
    @PostMapping("/customer")
    private ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        var customer1 = customerService.createCustomer(customer);
        rabbitTemplate.convertAndSend("x.post-registration","",customer1);
        return new ResponseEntity<Customer>(customer1,HttpStatus.ACCEPTED);
    }
}
