package com.example.pubsubregistrationsystem.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "user.registered.exchange";
    public static final String EMAIL_QUEUE = "email.queue";
    public static final String AUDIT_QUEUE = "audit.queue";

    // Create the fanout exchange to broadcast events.
    @Bean
    public FanoutExchange userRegisteredExchange() {
        return new FanoutExchange(EXCHANGE_NAME);
    }

    // Create a queue for the Email Service.
    @Bean
    public Queue emailQueue() {
        return new Queue(EMAIL_QUEUE);
    }

    // Create a queue for the Audit Service.
    @Bean
    public Queue auditQueue() {
        return new Queue(AUDIT_QUEUE);
    }

    // Bind the Email queue to receive every published event.
    @Bean
    public Binding emailBinding() {
        return BindingBuilder.bind(emailQueue())
                .to(userRegisteredExchange());
    }

    // Bind the Audit queue to receive every published event.
    @Bean
    public Binding auditBinding() {
        return BindingBuilder.bind(auditQueue())
                .to(userRegisteredExchange());
    }

    @Bean
    public JacksonJsonMessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }
}