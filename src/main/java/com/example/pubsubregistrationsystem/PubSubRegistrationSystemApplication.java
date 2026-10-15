package com.example.pubsubregistrationsystem;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class PubSubRegistrationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PubSubRegistrationSystemApplication.class, args);
    }
}
