package com.example.pubsubregistrationsystem.service;

import com.example.pubsubregistrationsystem.config.RabbitMQConfig;
import com.example.pubsubregistrationsystem.model.UserRegisteredEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final RabbitTemplate rabbitTemplate;

    public UserService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public UserRegisteredEvent registerUser(String name, String email) {
        UserRegisteredEvent event = new UserRegisteredEvent(name, email);

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                "",
                event
        );

        return event;
    }
}
