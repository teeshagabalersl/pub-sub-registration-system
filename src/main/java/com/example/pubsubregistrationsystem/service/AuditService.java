package com.example.pubsubregistrationsystem.service;

import com.example.pubsubregistrationsystem.config.RabbitMQConfig;
import com.example.pubsubregistrationsystem.model.UserRegisteredEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class AuditService {

    @RabbitListener(queues = RabbitMQConfig.AUDIT_QUEUE)
    public void logRegistration(UserRegisteredEvent event) {
        System.out.println(
                "Logging: New user signed up at " + event.getTimestamp()
        );
    }
}
