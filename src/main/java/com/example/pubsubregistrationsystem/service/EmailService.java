package com.example.pubsubregistrationsystem.service;

import com.example.pubsubregistrationsystem.config.RabbitMQConfig;
import com.example.pubsubregistrationsystem.model.UserRegisteredEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @RabbitListener(queues = RabbitMQConfig.EMAIL_QUEUE)
    public void sendWelcomeEmail(UserRegisteredEvent event) {
        System.out.println(
                "Sending Welcome Email to " + event.getName()
                        + " (" + event.getEmail() + ")"
        );
    }
}
