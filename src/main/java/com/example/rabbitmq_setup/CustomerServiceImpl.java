package com.example.rabbitmq_setup;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl {
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer){
        var customer1 = new Customer();
        customer1.setEmail(customer.getEmail());
        customer1.setFirstname(customer.getFirstname());
        return customer1;
    }
    @RabbitListener(queues={"q.customer-registration"})
    public void onCustomerRegistration(Customer event){
        log.info("user registration received: {}",event);

    }
}
