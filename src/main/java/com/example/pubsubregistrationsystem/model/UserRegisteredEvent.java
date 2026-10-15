package com.example.pubsubregistrationsystem.model;

import java.time.LocalDateTime;

public class UserRegisteredEvent implements java.io.Serializable {

    private String name;
    private String email;
    private LocalDateTime timestamp;

    public UserRegisteredEvent() {
    }

    public UserRegisteredEvent(String name, String email) {
        this.name = name;
        this.email = email;
        this.timestamp = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}