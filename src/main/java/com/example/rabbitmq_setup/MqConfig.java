package com.example.rabbitmq_setup;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {
    private CachingConnectionFactory connectionFactory;

    public MqConfig(CachingConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public static final String QUEUE = "q.customer-registration";
    public static final String EXCHANGE = "Notification Exchange";
//    public static final String ROUTING_KEY = "Notification Routing Key";

    @Bean
    public Queue queue(){
        return new Queue(QUEUE);
    }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }
//    @Bean
//    public Binding bindings(Queue queue, TopicExchange topicExchange){
//        return BindingBuilder
//                .bind(queue)
//                .to(topicExchange)
//                .with(ROUTING_KEY);
//    }
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate notificationRabbitTemplate(){
        final RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    public Declarables createCustomerRegistrationSchema(){
        return new Declarables(
                new FanoutExchange("x.post-registration"),
                new Queue("q.send-email"),
                new Queue("q.send-sms"),
                new Binding("q.send-email",Binding.DestinationType.QUEUE,
                        "x.post-registration","send-email",null),
                new Binding("q.send-sms", Binding.DestinationType.QUEUE,
                        "x.post-registration", "send-sms", null)
        );
    }

}
